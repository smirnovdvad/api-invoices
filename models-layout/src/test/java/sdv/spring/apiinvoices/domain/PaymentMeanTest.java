package sdv.spring.apiinvoices.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMeanTest {

    PaymentMean paymentMean;
    Long id = 88L;
    String description = "Avans 10 %";

    @BeforeEach
    public void setUp(){
        paymentMean = PaymentMean.builder()
                .description(this.description)
                .id(this.id)
                .build();
    }

    @Test
    void getId() {
        assertEquals(this.id, paymentMean.getId());
    }

    @Test
    void getDescription() {
        assertEquals(this.description, paymentMean.getDescription());
    }
}