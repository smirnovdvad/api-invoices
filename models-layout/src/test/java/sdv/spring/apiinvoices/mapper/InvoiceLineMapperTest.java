package sdv.spring.apiinvoices.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sdv.spring.apiinvoices.domain.InvoiceLine;
import sdv.spring.apiinvoices.initializer.TestInizializer;
import sdv.spring.apiinvoices.model.InvoiceLineDTO;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceLineMapperTest extends TestInizializer {

    InvoiceLineMapper invoiceLineMapper = InvoiceLineMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        this.initUnitOfMeasure();
        this.initUnitOfMeasureDTO();
        this.initGoodDTO();
        this.initGoodDTO();
        this.initInvoiceLine();
        this.initInvoiceLineDTO();
    }

    @Test
    void invoiceLineToInvoiceLineDTO() {
        InvoiceLineDTO invoiceLineDTO = invoiceLineMapper.invoiceLineToInvoiceLineDTO(this.invoiceLine);
        assertEquals(invoiceLineDTO.getLinenumber(),this.invoiceLine.getLinenumber());
    }

    @Test
    void invoiceLineDtoToInvoiceLine() {
        InvoiceLine invoiceLine = invoiceLineMapper.invoiceLineDtoToInvoiceLine(this.invoiceLineDTO);
        assertEquals(invoiceLine.getLinenumber(),this.invoiceLineDTO.getLinenumber());
    }
}