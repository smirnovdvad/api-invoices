package sdv.spring.apiinvoices.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceLineTest {

    Good good;
    UnitOfMeasure unitOfMeasure;
    Currency usd;
    String invoiceLineNumber = "0100";
    Long invoiceLineId = 77L;
    BigDecimal quantity = new BigDecimal(1.00);
    BigDecimal baseAmount = new BigDecimal(100);
    InvoiceLine invoiceLine;

    @BeforeEach
    public void setUp(){
        UnitOfMeasure unitOfMeasure = UnitOfMeasure.builder()
                .name("KG")
                .description("Kilos").build();

        good = Good.builder()
                .name("Patotes")
                .uom(unitOfMeasure)
                .build();

        usd = Currency.getInstance("USD");

        invoiceLine = InvoiceLine.builder()
                .good(good)
                .linenumber(this.invoiceLineNumber)
                .id(this.invoiceLineId)
                .quantity(this.quantity)
                .baseamount(this.baseAmount)
                .curr(this.usd)
                .build();
    }

    @Test
    void getId() {
        assertEquals(this.invoiceLineId, invoiceLine.getId());
    }

    @Test
    void getInvoice() {
        assertEquals(null, invoiceLine.getInvoice());
    }

    @Test
    void getLinenumber() {
        assertEquals(this.invoiceLineNumber, invoiceLine.getLinenumber());
    }

    @Test
    void getGood() {
        assertEquals(this.good, invoiceLine.getGood());
    }

    @Test
    void getQuantity() {
        assertEquals(this.quantity, invoiceLine.getQuantity());
    }

    @Test
    void getBaseamount() {
        assertEquals(this.baseAmount, invoiceLine.getBaseamount());
    }

    @Test
    void getCurr() {
        assertEquals(this.usd, invoiceLine.getCurr());
    }
}