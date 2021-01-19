package sdv.spring.apiinvoices.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sdv.spring.apiinvoices.domain.PaymentMean;
import sdv.spring.apiinvoices.initializer.TestInizializer;
import sdv.spring.apiinvoices.model.PaymentMeanDTO;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMeanMapperTest extends TestInizializer {

    PaymentMeanMapper paymentMeanMapper = PaymentMeanMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        this.initPaymentMean();
        this.initPaymentMeanDTO();
    }

    @Test
    void paymentMeanToPaymentMeanDTO() {
        PaymentMeanDTO paymentMeanDTO = paymentMeanMapper.paymentMeanToPaymentMeanDTO(this.paymentMean1);
        assertEquals(paymentMeanDTO.getDescription(),this.paymentMean1.getDescription());
    }

    @Test
    void paymentMeanDtoToPaymentMean() {
        PaymentMean paymentMean = paymentMeanMapper.paymentMeanDtoToPaymentMean(this.paymentMeanDTO);
        assertEquals(paymentMean.getDescription(),this.paymentMeanDTO.getDescription());
    }
}