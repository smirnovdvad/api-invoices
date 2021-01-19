package sdv.spring.apiinvoices.services.springdatajpa;

import lombok.extern.slf4j.Slf4j;
import sdv.spring.apiinvoices.domain.Company;
import sdv.spring.apiinvoices.domain.Invoice;
import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.PaymentMean;
import sdv.spring.apiinvoices.exception.InvoiceDuplicateNumber;
import sdv.spring.apiinvoices.exception.InvoiceNotFoundException;
import sdv.spring.apiinvoices.mapper.InvoiceMapper;
import sdv.spring.apiinvoices.model.InvoiceDTO;
import sdv.spring.apiinvoices.repository.InvoiceRepository;
import sdv.spring.apiinvoices.services.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.*;
import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InvoiceJPAService implements InvoiceService {

    private InvoiceMapper invoiceMapper = InvoiceMapper.INSTANCE;

    public InvoiceJPAService(InvoiceRepository invoiceRepository, CompanyService companyService, InvoiceLineService invoiceLineService,
                             PaymentMeanService paymentMeanService, GoodService goodService) {
        this.invoiceRepository = invoiceRepository;
        this.companyService = companyService;
        this.invoiceLineService = invoiceLineService;
        this.paymentMeanService = paymentMeanService;
        this.goodService = goodService;
    }

    private final InvoiceRepository invoiceRepository;
    private final CompanyService companyService;
    private final InvoiceLineService invoiceLineService;
    private final PaymentMeanService paymentMeanService;
    private final GoodService goodService;

    @Override
    public Set<Invoice> findAll() {
        HashSet<Invoice> invoices = new HashSet<>();
        invoiceRepository.findAll().forEach(invoice -> invoices.add(invoice));
        return invoices;
    }

    @Override
    public Invoice findById(Long aLong) {
        Optional<Invoice> invoice;
        invoice = invoiceRepository.findById(aLong);
        if (invoice.isPresent())
            return invoice.get();
        else
            return null;

    }

    @Override
    public Invoice save(Invoice object) {
        Company companytmp;
        companytmp = companyService.findByTin(object.getCompanyissuer().getTin());
        if (companytmp != null)
            object.getCompanyissuer().setId(companytmp.getId());
        else
            companyService.save(object.getCompanyissuer());

        companytmp = companyService.findByTin(object.getCompanyreceiver().getTin());
        if (companytmp != null)
            object.getCompanyreceiver().setId(companytmp.getId());
        else
            companyService.save(object.getCompanyreceiver());

        object.getPaymentmeans().forEach(paymentMean -> {
            PaymentMean paymentMeanTmp;
            paymentMeanTmp = paymentMeanService.findByDescription(paymentMean.getDescription());
            if ( paymentMeanTmp != null)
                paymentMean.setId(paymentMeanTmp.getId());
            else
                paymentMeanService.save(paymentMean);
        });

        object.getInvoicelines().forEach(invoiceLine -> {
            goodService.save(invoiceLine.getGood());
            invoiceLine.setInvoice(object);
        });
        return invoiceRepository.save(object);
    }

    @Override
    public void delete(Invoice object) {
        invoiceRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        invoiceRepository.deleteById(aLong);
    }

    @Override
    public InvoiceDTO postInvoiceDTO(InvoiceDTO invoiceDTO) {
        Optional<Invoice> optInvoice = invoiceRepository.findByNumber(
                invoiceDTO.getNumber()
        );
        if (optInvoice.isPresent() &&
                invoiceMapper.invoiceDtoToInvoice(invoiceDTO).getCompanyissuer()
                        .equals(optInvoice.get().getCompanyissuer()))
            throw new InvoiceDuplicateNumber("This Invoice already exists. Before posting new document you need " +
                    "to reverse old one");
        return invoiceMapper.invoiceToInvoiceDTO(save(
                invoiceMapper.invoiceDtoToInvoice(invoiceDTO)
        ));
    }

    @Override
    public Set<InvoiceDTO> getAllInvoicesDTO() {
        HashSet<InvoiceDTO> resInvoicesDTO = new HashSet<>();
        findAll().forEach(
                invoice -> {
                    resInvoicesDTO.add(invoiceMapper.invoiceToInvoiceDTO(invoice));
                }
        );
        return resInvoicesDTO;
    }

    @Override
    public InvoiceDTO putInvoiceDTO(InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceMapper.invoiceDtoToInvoice(invoiceDTO);
        Optional<Invoice> optInvoice = invoiceRepository.findByNumber(invoice.getNumber());
        if (optInvoice.isPresent())
            invoice.setId(optInvoice.get().getId());

        return invoiceMapper.invoiceToInvoiceDTO(save(invoice));
    }

    @Override
    public InvoiceDTO getInvoiceDTOByNumber(String aInvNumber) {
        Optional<Invoice> optInvoice = invoiceRepository.findByNumber(aInvNumber);
        if (optInvoice.isPresent())
            return invoiceMapper.invoiceToInvoiceDTO(optInvoice.get());
        else
            throw new InvoiceNotFoundException("Invoice does not exist");
    }

    @Override
    public Invoice findByNumber(String aInvNumber) {
        Optional<Invoice> optInvoice = invoiceRepository.findByNumber(aInvNumber);
        if (optInvoice.isPresent())
            return optInvoice.get();
        else
            throw new InvoiceNotFoundException("Invoice does not exist");
    }
}
