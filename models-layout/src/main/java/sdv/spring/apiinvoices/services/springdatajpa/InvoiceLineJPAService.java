package sdv.spring.apiinvoices.services.springdatajpa;

import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.Good;
import sdv.spring.apiinvoices.domain.Invoice;
import sdv.spring.apiinvoices.domain.InvoiceLine;
import sdv.spring.apiinvoices.repository.InvoiceLineRepository;
import sdv.spring.apiinvoices.services.GoodService;
import sdv.spring.apiinvoices.services.InvoiceLineService;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class InvoiceLineJPAService implements InvoiceLineService {

    private final InvoiceLineRepository invoicelinerepository;
    private final GoodService goodService;

    public InvoiceLineJPAService(InvoiceLineRepository invoicelinerepository, GoodService goodService) {
        this.invoicelinerepository = invoicelinerepository;
        this.goodService = goodService;
    }

    @Override
    public Set<InvoiceLine> findAll() {
        HashSet<InvoiceLine> invoicelines = new HashSet<>();
        invoicelinerepository.findAll().forEach(invoiceline -> invoicelines.add(invoiceline));
        return invoicelines;
    }

    @Override
    public InvoiceLine findById(Long aLong) {
        Optional<InvoiceLine> invoiceline;
        invoiceline = invoicelinerepository.findById(aLong);
        if (invoiceline.isPresent())
            return invoiceline.get();
        else
            return null;
    }

    @Override
    public InvoiceLine save(InvoiceLine object) {
        Good goodTmp = goodService.findByName(object.getGood().getName());
        if (goodTmp != null)
            object.getGood().setId(goodTmp.getId());
        else
            goodService.save(object.getGood());
        return invoicelinerepository.save(object);
    }

    @Override
    public void delete(InvoiceLine object) {
        invoicelinerepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        invoicelinerepository.deleteById(aLong);
    }

    @Override
    public InvoiceLine findByLineNumberAndInvoice(String lineNumber, Invoice invoice){
        Optional<InvoiceLine> optionalInvoiceLine =
        invoicelinerepository.findInvoiceLineByLinenumberAndInvoice(lineNumber,invoice);
        if (optionalInvoiceLine.isPresent())
            return optionalInvoiceLine.get();
        else
            return null;
    }
}
