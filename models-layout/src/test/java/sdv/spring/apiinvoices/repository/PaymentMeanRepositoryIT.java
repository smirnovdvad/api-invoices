package sdv.spring.apiinvoices.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import sdv.spring.apiinvoices.domain.Company;
import sdv.spring.apiinvoices.domain.PaymentMean;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class )
@DataJpaTest
public class PaymentMeanRepositoryIT {

    @Autowired
    PaymentMeanRepository paymentMeanRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() {
        Optional<PaymentMean> paymentMean = paymentMeanRepository.findByDescription("Free");
        assertEquals("Free", paymentMean.get().getDescription());
    }
}