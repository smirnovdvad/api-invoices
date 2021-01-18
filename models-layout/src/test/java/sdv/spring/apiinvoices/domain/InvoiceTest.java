package sdv.spring.apiinvoices.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sdv.spring.apiinvoices.initializer.TestInizializer;

import static org.junit.jupiter.api.Assertions.assertEquals;


class InvoiceTest extends TestInizializer {


    @BeforeEach
    public void setUp(){

        this.completeInit();
    }

    @Test
    void getId() {
        assertEquals(this.invoiceId, invoice.getId());
    }

    @Test
    void getNumber() {
        assertEquals(this.invoiceNumber, invoice.getNumber());
    }

    @Test
    void getDate() {
        assertEquals(this.invoiceDate, invoice.getDate());
    }

    @Test
    void getCompanyissuer() {
        assertEquals(this.companyIssuer, invoice.getCompanyissuer());
    }

    @Test
    void getCompanyreceiver() {
        assertEquals(this.companyReceiver, invoice.getCompanyreceiver());
    }

    @Test
    void getPaymentmeans() {
        assertEquals(this.pm, invoice.getPaymentmeans());
        assertEquals(this.pm.stream().count(), invoice.getPaymentmeans().stream().count());
    }

    @Test
    void getInvoicelines() {
        assertEquals(this.invLines, invoice.getInvoicelines());
        assertEquals(this.invLines.stream().count(), invoice.getInvoicelines().stream().count());
    }

}