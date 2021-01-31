package sdv.spring.apiinvoices.services;

import sdv.spring.apiinvoices.domain.Invoice;
import sdv.spring.apiinvoices.model.CompanyDTO;
import sdv.spring.apiinvoices.model.InvoiceDTO;

import java.util.Set;

public interface InvoiceService extends CrudService<Invoice,Long>{

    public InvoiceDTO postInvoiceDTO(InvoiceDTO invoiceDTO);
    public Set<InvoiceDTO> getAllInvoicesDTO();
    public InvoiceDTO putInvoiceDTO(InvoiceDTO invoiceDTO);
    public InvoiceDTO getInvoiceDTOByNumber(String aInvNumber);
    public Invoice findByNumber(String aInvNumber);
    public Invoice findByNumberAndCompanyIssuerAndIsReversed(String aInvNumber, CompanyDTO companyIssuer, Boolean isReversed);
    public Invoice findByNumberAndIsReversed(String aInvNumber, Boolean isReversed);
}
