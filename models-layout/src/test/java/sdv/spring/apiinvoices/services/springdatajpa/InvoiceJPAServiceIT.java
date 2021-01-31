package sdv.spring.apiinvoices.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sdv.spring.apiinvoices.domain.Invoice;
import sdv.spring.apiinvoices.initializer.TestInizializer;
import sdv.spring.apiinvoices.repository.InvoiceLineRepository;
import sdv.spring.apiinvoices.repository.InvoiceRepository;
import sdv.spring.apiinvoices.services.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InvoiceJPAServiceIT extends TestInizializer {

    @Mock
    InvoiceRepository invoiceRepository;

    @Mock
    CompanyService companyService;

    @Mock
    InvoiceLineService invoiceLineService;

    @Mock
    InvoiceLineRepository invoiceLineRepository;

    @Mock
    PaymentMeanService paymentMeanService;

    @Mock
    GoodService goodService;

    InvoiceService invoiceService;

    @BeforeEach
    void SetUp(){
        MockitoAnnotations.openMocks(this);
        invoiceService = new InvoiceJPAService(invoiceRepository,companyService,invoiceLineService,
                paymentMeanService,goodService);

        this.completeInit();
    }

    @Test
    void findAll() {
        when(invoiceRepository.findAll()).thenReturn(this.invoices);
        assertEquals(1,invoiceService.findAll().size());
        verify(invoiceRepository,times(1)).findAll();
        verify(invoiceRepository,never()).findById(anyLong());
    }

    @Test
    void findById() {
        Optional<Invoice> optInvoice = Optional.of(invoice);
        when(invoiceRepository.findById(anyLong())).thenReturn(optInvoice);
        assertEquals(55L,invoiceService.findById(1L).getId());
        verify(invoiceRepository,never()).findAll();
    }

    @Test
    void save() {
        when(invoiceRepository.save(any())).thenReturn(invoice);
        when(invoiceLineService.findByLineNumberAndInvoice(any(),any())).
                thenReturn(invoice.getInvoicelines().stream().findAny().get());
        assertEquals(55L,invoiceService.save(invoice).getId());
        verify(companyService,times(2)).findByTin(anyString());
        verify(companyService,times(2)).save(any());
        verify(paymentMeanService,times(2)).findByDescription(anyString());
        //verify(paymentMeanService,times(2)).save(any());
        verify(goodService,times(1)).save(any());
    }

    @Test
    void delete() {
        invoiceService.delete(invoice);
        verify(invoiceRepository,times(1)).delete(any());
        verify(invoiceRepository,never()).deleteById(anyLong());
    }

    @Test
    void deleteById() {
        invoiceService.deleteById(55L);
        verify(invoiceRepository,times(1)).deleteById(55L);
        verify(invoiceRepository,never()).delete(any());
    }
}