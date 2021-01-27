package sdv.spring.apiinvoices.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sdv.spring.apiinvoices.domain.CBRFResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CBRFServiceIT {

    @Autowired
    CBRFService cbrfService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getCurrencyFromCBRF() throws ParseException {
//        CBRFResponse cbrfResponse = cbrfService.getCurrencyFromCBRF(new SimpleDateFormat("ddmmyyyy").parse("26012021"));
//        assertNotNull(cbrfResponse);
    }
}