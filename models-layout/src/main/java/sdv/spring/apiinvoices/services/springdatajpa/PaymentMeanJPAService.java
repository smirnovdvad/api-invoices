package sdv.spring.apiinvoices.services.springdatajpa;

import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.PaymentMean;
import sdv.spring.apiinvoices.repository.PaymentMeanRepository;
import sdv.spring.apiinvoices.services.PaymentMeanService;

import java.util.Optional;

@Service
public class PaymentMeanJPAService extends CrudServiceCommon<PaymentMean,Long,PaymentMeanRepository> implements PaymentMeanService {

    PaymentMeanJPAService(PaymentMeanRepository paymentMeanRepository){
        super(paymentMeanRepository);
    }

    @Override
    public PaymentMean findByDescription(String aDescription) {
        Optional<PaymentMean> paymentMean =
                this.getRepository().findByDescription(aDescription);
        if (paymentMean.isPresent())
            return paymentMean.get();
        else
            return null;
    }
}
