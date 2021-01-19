package sdv.spring.apiinvoices.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sdv.spring.apiinvoices.domain.InvoiceLine;
import sdv.spring.apiinvoices.model.InvoiceLineDTO;

@Mapper
public interface InvoiceLineMapper {
    InvoiceLineMapper INSTANCE = Mappers.getMapper(InvoiceLineMapper.class);


    InvoiceLineDTO invoiceLineToInvoiceLineDTO(InvoiceLine invoiceLine);

    InvoiceLine invoiceLineDtoToInvoiceLine (InvoiceLineDTO invoiceLineDTO);
}
