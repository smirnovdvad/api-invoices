package sdv.spring.apiinvoices.services.springdatajpa;

import sdv.spring.apiinvoices.domain.Company;
import sdv.spring.apiinvoices.domain.Invoice;
import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.PaymentMean;
import sdv.spring.apiinvoices.repository.InvoiceRepository;
import sdv.spring.apiinvoices.services.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class InvoiceJPAService implements InvoiceService {

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
        if (companytmp == null)
            companyService.save(object.getCompanyissuer());
        else
            object.getCompanyissuer().setId(companytmp.getId());

        companytmp = companyService.findByTin(object.getCompanyreceiver().getTin());
        if ( companytmp == null)
            companyService.save(object.getCompanyreceiver());
        else
            object.getCompanyreceiver().setId(companytmp.getId());

        object.getPaymentmeans().forEach(paymentMean -> {
            PaymentMean paymentMeanTmp;
            paymentMeanTmp = paymentMeanService.findByDescription(paymentMean.getDescription());
            if ( paymentMeanTmp== null)
                paymentMeanService.save(paymentMean);
            else
                paymentMean.setId(paymentMeanTmp.getId());
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
}
