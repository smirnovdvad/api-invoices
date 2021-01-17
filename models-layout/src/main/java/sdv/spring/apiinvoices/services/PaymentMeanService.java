package sdv.spring.apiinvoices.services;

import sdv.spring.apiinvoices.domain.PaymentMean;

public interface PaymentMeanService extends CrudService<PaymentMean,Long>{
    PaymentMean findByDescription(String aDescription);
}
