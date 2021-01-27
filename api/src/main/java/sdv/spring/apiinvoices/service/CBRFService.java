package sdv.spring.apiinvoices.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sdv.spring.apiinvoices.domain.CBRFResponse;
import sdv.spring.apiinvoices.domain.CurrencyCBRFNode;
import sdv.spring.apiinvoices.exception.WrongInvoiceDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CBRFService {

    private final String BASE_CBRF_URL = "http://www.cbr.ru/scripts/XML_daily_eng.asp?date_req=";

    @Cacheable("currencies")
    public List<CurrencyCBRFNode> getCurrencyRateOnTheDate(Currency curr, Date date){
        log.info("I am inside getCurrencyRateOnTheDate method");

        CBRFResponse cbrfResponse = getCurrenciesFromCBRF(date);
        List<CurrencyCBRFNode> res = cbrfResponse.getCurrencyCBRFNodeSet().stream().filter(
                item-> item.getCharCode().equals(curr.getCurrencyCode())
        ).collect(Collectors.toList());

        return res;
    }

    private CBRFResponse getCurrenciesFromCBRF(Date date){
        String urlForRequest = this.BASE_CBRF_URL + getURLForRequest(date);
        RestTemplate restTemplate = new RestTemplate();

        CBRFResponse cbrfResponse = restTemplate.getForObject(urlForRequest,CBRFResponse.class);

        return cbrfResponse;
    };

    private String getURLForRequest(Date date){
        if (date == null || date.toString()==""){
            log.error("Passed date is incorrect to CBRFService");
            throw new WrongInvoiceDate("Invoice Date is incorrect");
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);

    }

}
