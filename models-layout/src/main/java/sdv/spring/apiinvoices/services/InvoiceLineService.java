package sdv.spring.apiinvoices.services;

import sdv.spring.apiinvoices.domain.Invoice;
import sdv.spring.apiinvoices.domain.InvoiceLine;

import java.security.Provider;

public interface InvoiceLineService extends CrudService<InvoiceLine,Long>{
    InvoiceLine findByLineNumberAndInvoice(String lineNumber, Invoice invoice);
}
