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
public class InvoiceLineJPAService extends CrudServiceCommon<InvoiceLine,Long,InvoiceLineRepository>
                                   implements InvoiceLineService{

    private final GoodService goodService;

    InvoiceLineJPAService(InvoiceLineRepository invoiceLineRepository, GoodService goodService){
        super(invoiceLineRepository);
        this.goodService = goodService;
    }

    @Override
    public InvoiceLine findByLineNumberAndInvoice(String lineNumber, Invoice invoice){
        Optional<InvoiceLine> optionalInvoiceLine =
        this.getRepository().findInvoiceLineByLinenumberAndInvoice(lineNumber,invoice);
        if (optionalInvoiceLine.isPresent())
            return optionalInvoiceLine.get();
        else
            return null;
    }
}
