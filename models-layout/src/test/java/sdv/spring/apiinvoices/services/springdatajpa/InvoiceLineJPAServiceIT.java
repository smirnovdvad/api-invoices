package sdv.spring.apiinvoices.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sdv.spring.apiinvoices.domain.InvoiceLine;
import sdv.spring.apiinvoices.initializer.TestInizializer;
import sdv.spring.apiinvoices.repository.InvoiceLineRepository;
import sdv.spring.apiinvoices.services.GoodService;
import sdv.spring.apiinvoices.services.InvoiceLineService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InvoiceLineJPAServiceIT extends TestInizializer {

    @Mock
    InvoiceLineRepository invoiceLineRepository;

    @Mock
    GoodService goodService;

    InvoiceLineService invoiceLineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        invoiceLineService = new InvoiceLineJPAService(invoiceLineRepository,goodService);
        this.initCurrency();
        this.initUnitOfMeasure();
        this.initGood();
        this.initInvoiceLine();
    }

    @Test
    void findAll() {
        when(invoiceLineRepository.findAll()).thenReturn(this.invLines);
        assertEquals(1,invoiceLineService.findAll().size());
        verify(invoiceLineRepository,times(1)).findAll();
        verify(invoiceLineRepository,never()).findById(anyLong());
    }

    @Test
    void findById() {
        Optional<InvoiceLine> optInvoiceLine = Optional.of(this.invoiceLine);
        when(invoiceLineRepository.findById(anyLong())).thenReturn(optInvoiceLine);
        assertEquals(33L,invoiceLineService.findById(11L).getId());
        verify(invoiceLineRepository,times(1)).findById(anyLong());
        verify(invoiceLineRepository,never()).findAll();
    }

    @Test
    void save() {
        when(invoiceLineRepository.save(any())).thenReturn(this.invoiceLine);
        assertEquals(33L,invoiceLineService.save(this.invoiceLine).getId());
        verify(goodService,times(1)).findByName(anyString());
        verify(goodService,times(1)).save(any());
    }

    @Test
    void delete() {
        invoiceLineService.delete(this.invoiceLine);
        verify(invoiceLineRepository,times(1)).delete(this.invoiceLine);
        verify(invoiceLineRepository,never()).deleteById(anyLong());
    }

    @Test
    void deleteById() {
        invoiceLineService.deleteById(this.invoiceLine.getId());
        verify(invoiceLineRepository,times(1)).deleteById(this.invoiceLine.getId());
        verify(invoiceLineRepository,never()).delete(any());
    }
}