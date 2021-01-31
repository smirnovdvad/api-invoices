package sdv.spring.apiinvoices.services.springdatajpa;

import lombok.extern.slf4j.Slf4j;
import sdv.spring.apiinvoices.domain.*;
import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.exception.InvoiceDuplicateNumber;
import sdv.spring.apiinvoices.exception.InvoiceLineNotFoundException;
import sdv.spring.apiinvoices.exception.InvoiceNotFoundException;
import sdv.spring.apiinvoices.mapper.CompanyMapper;
import sdv.spring.apiinvoices.mapper.InvoiceMapper;
import sdv.spring.apiinvoices.model.CompanyDTO;
import sdv.spring.apiinvoices.model.InvoiceDTO;
import sdv.spring.apiinvoices.repository.InvoiceRepository;
import sdv.spring.apiinvoices.services.*;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class InvoiceJPAService implements InvoiceService {

    private InvoiceMapper invoiceMapper = InvoiceMapper.INSTANCE;
    private CompanyMapper companyMapper = CompanyMapper.INSTANCE;

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
    @Transactional
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
            {
                paymentMean.setId(paymentMeanTmp.getId());
                paymentMean.getInvoices().add(object);
            }
            else
                paymentMeanService.save(paymentMean);
        });

        object.getInvoicelines().forEach(invoiceLine -> {
            InvoiceLine savedInvoiceLine = null;
            invoiceLine.getGood().getInvoiceLines().add(invoiceLine);
            Good good = goodService.findByName(invoiceLine.getGood().getName());

            if (good != null)
                invoiceLine.getGood().setId(good.getId());
            else
                goodService.save(invoiceLine.getGood());
            invoiceLine.setInvoice(object);
            if (object.getId() != null)
            {
                log.info(object.getId().toString());
                savedInvoiceLine =
                        invoiceLineService.findByLineNumberAndInvoice(invoiceLine.getLinenumber(),object);
                if (savedInvoiceLine == null)
                    throw new InvoiceLineNotFoundException("Invoice Line with number "+ invoiceLine.getLinenumber() +
                            " can not be updated due to not existence");
            }
            if (savedInvoiceLine!=null)
                invoiceLine.setId(savedInvoiceLine.getId());
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
        Optional<Invoice> optInvoice = invoiceRepository.findByNumberAndIsReversed(
                invoiceDTO.getNumber(),
                Boolean.valueOf("FALSE") );

        if (optInvoice.isPresent() &&
                invoiceMapper.invoiceDtoToInvoice(invoiceDTO).getCompanyissuer()
                        .equals(optInvoice.get().getCompanyissuer()) &&
            optInvoice.get().getIsReversed() == false)
        {
            log.error("Invoice already exist + " + invoiceDTO.toString());
            throw new InvoiceDuplicateNumber("This Invoice already exists. Before posting new document you need " +
                    "to reverse old one");

        }

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
        Optional<Invoice> optInvoice = invoiceRepository.findByNumberAndIsReversed(invoice.getNumber(),
                Boolean.valueOf("FALSE"));
        if (optInvoice.isPresent())
        {
            invoice.setId(optInvoice.get().getId());
//            if (invoice.getIsReversed())
//                throw new UpdateIsNotPossibleException("Update is not possible. Document is reversed, but you can create " +
//                        "new document");
        }

        return invoiceMapper.invoiceToInvoiceDTO(save(invoice));
    }

    @Override
    public InvoiceDTO getInvoiceDTOByNumber(String aInvNumber) {
        Optional<Invoice> optInvoice = invoiceRepository.findByNumber(aInvNumber);
        if (optInvoice.isPresent())
            return invoiceMapper.invoiceToInvoiceDTO(optInvoice.get());
        else{
            log.error("Invoice with number " + aInvNumber + " does not exist");
            throw new InvoiceNotFoundException("Invoice does not exist");
        }
    }

    @Override
    public Invoice findByNumber(String aInvNumber) {
        Optional<Invoice> optInvoice = invoiceRepository.findByNumber(aInvNumber);
        if (optInvoice.isPresent())
            return optInvoice.get();
        else
            throw new InvoiceNotFoundException("Invoice does not exist");
    }

    @Override
    public Invoice findByNumberAndCompanyIssuerAndIsReversed(String aInvNumber, CompanyDTO companyIssuer, Boolean isReversed) {
        Optional<Invoice> optInvoice = invoiceRepository.findByNumberAndCompanyIssuerAndIsReversed(
                aInvNumber, companyMapper.companyDtoToCompany(companyIssuer), isReversed
        );
        if (optInvoice.isPresent())
            return optInvoice.get();
        else
            return null;
    }

    @Override
    public Invoice findByNumberAndIsReversed(String aInvNumber, Boolean isReversed) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findByNumberAndIsReversed(aInvNumber,Boolean.valueOf("FALSE"));
        if (optionalInvoice.isPresent())
            return optionalInvoice.get();
        else
            return null;
    }
}
