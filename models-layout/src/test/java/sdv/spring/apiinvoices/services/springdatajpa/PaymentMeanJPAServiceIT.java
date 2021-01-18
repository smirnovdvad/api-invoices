package sdv.spring.apiinvoices.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sdv.spring.apiinvoices.initializer.TestInizializer;
import sdv.spring.apiinvoices.repository.PaymentMeanRepository;
import sdv.spring.apiinvoices.services.PaymentMeanService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentMeanJPAServiceIT extends TestInizializer {

    @Mock
    PaymentMeanRepository paymentMeanRepository;

    PaymentMeanService paymentMeanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentMeanService = new PaymentMeanJPAService(paymentMeanRepository);
        this.initPaymentMean();
    }

    @Test
    void findAll() {
        when(paymentMeanRepository.findAll()).thenReturn(this.pm);
        assertEquals(2,paymentMeanService.findAll().size());
        verify(paymentMeanRepository,times(1)).findAll();
        verify(paymentMeanRepository,never()).findById(anyLong());
    }

}