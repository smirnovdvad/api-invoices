package sdv.spring.apiinvoices.services.springdatajpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sdv.spring.apiinvoices.domain.Invoice;
import sdv.spring.apiinvoices.exception.InvoiceNotFoundException;
import sdv.spring.apiinvoices.initializer.TestInizializer;
import sdv.spring.apiinvoices.mapper.InvoiceMapper;
import sdv.spring.apiinvoices.model.InvoiceDTO;
import sdv.spring.apiinvoices.services.*;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class )
@SpringBootTest
public class InvoiceJPAService4RestIT extends TestInizializer {

    @Autowired
    InvoiceService invoiceService;


    InvoiceMapper invoiceMapper = InvoiceMapper.INSTANCE;

    @Before
    public void setUp() throws Exception {
        this.completeInit();
    }

    @Test
    @Transactional
    public void postInvoiceDTO() {
        InvoiceDTO invoiceDTO = invoiceMapper.invoiceToInvoiceDTO(this.invoice);
        invoiceService.postInvoiceDTO(invoiceDTO);

        assertEquals(1, invoiceService.findAll().size());
        assertNotNull(invoiceService.findByNumber(this.invoiceNumber));
    }

    @Test
    @Transactional
    public void getAllInvoicesDTO() {
        InvoiceDTO invoiceDTO = invoiceMapper.invoiceToInvoiceDTO(this.invoice);
        invoiceService.postInvoiceDTO(invoiceDTO);
        assertEquals(1,invoiceService.getAllInvoicesDTO().size());
    }

    @Test
    @Transactional
    public void putInvoiceDTO() {
        this.invoice.setNumber("Inv2");
        InvoiceDTO temp =  invoiceService.putInvoiceDTO(invoiceMapper.invoiceToInvoiceDTO(this.invoice));
        Invoice tempInvoice = invoiceMapper.invoiceDtoToInvoice(temp);
        assertEquals("Inv2",tempInvoice.getNumber());
    }

    @Test(expected = InvoiceNotFoundException.class)
    public void getInvoiceDTOByNumber() {
        invoiceService.getInvoiceDTOByNumber("INV3");
    }
}