package sdv.spring.apiinvoices.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sdv.spring.apiinvoices.domain.Invoice;
import sdv.spring.apiinvoices.initializer.TestInizializer;
import sdv.spring.apiinvoices.model.InvoiceDTO;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceMapperTest extends TestInizializer {

    InvoiceMapper invoiceMapper = InvoiceMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        this.completeInit();
    }

    @Test
    void invoiceToInvoiceDTO() {
        InvoiceDTO invoiceDTO = invoiceMapper.invoiceToInvoiceDTO(this.invoice);
        assertEquals(invoiceDTO.getNumber(),this.invoice.getNumber());
    }

    @Test
    void invoiceDtoToInvoice() {
        Invoice invoice = invoiceMapper.invoiceDtoToInvoice(this.invoiceDTO);
        assertEquals(invoice.getDate(),this.invoiceDTO.getDate());
    }
}