package sdv.spring.apiinvoices.services.springdatajpa;

import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.PaymentMean;
import sdv.spring.apiinvoices.repository.PaymentMeanRepository;
import sdv.spring.apiinvoices.services.PaymentMeanService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PaymentMeanJPAService implements PaymentMeanService {

    private final PaymentMeanRepository paymentmeanrepository;

    public PaymentMeanJPAService(PaymentMeanRepository paymentmeanrepository) {
        this.paymentmeanrepository = paymentmeanrepository;
    }

    @Override
    public Set<PaymentMean> findAll() {
        HashSet<PaymentMean> paymentMeans = new HashSet<>();
        paymentmeanrepository.findAll().forEach(paymentMean -> paymentMeans.add(paymentMean));
        return paymentMeans;
    }

    @Override
    public PaymentMean findById(Long aLong) {
        Optional<PaymentMean> paymentmean =
                paymentmeanrepository.findById(aLong);
        if (paymentmean.isPresent())
            return paymentmean.get();
        else
            return null;
    }

    @Override
    public PaymentMean save(PaymentMean object) {
        return paymentmeanrepository.save(object);
    }

    @Override
    public void delete(PaymentMean object) {
        paymentmeanrepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        paymentmeanrepository.deleteById(aLong);
    }

    @Override
    public PaymentMean findByDescription(String aDescription) {
        Optional<PaymentMean> paymentMean =
                paymentmeanrepository.findByDescription(aDescription);
        if (paymentMean.isPresent())
            return paymentMean.get();
        else
            return null;
    }
}
