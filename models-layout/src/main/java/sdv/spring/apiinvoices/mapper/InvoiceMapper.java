package sdv.spring.apiinvoices.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sdv.spring.apiinvoices.domain.Invoice;
import sdv.spring.apiinvoices.model.InvoiceDTO;

@Mapper
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);


    InvoiceDTO invoiceToInvoiceDTO(Invoice invoice);

    Invoice invoiceDtoToInvoice (InvoiceDTO invoiceDTO);
}
