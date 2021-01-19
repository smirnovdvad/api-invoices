package sdv.spring.apiinvoices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import sdv.spring.apiinvoices.domain.PaymentMean;

import java.util.Optional;

public interface PaymentMeanRepository extends CrudRepository<PaymentMean,Long>{
    Optional<PaymentMean> findByDescription(String aDescription);
}
