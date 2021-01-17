package sdv.spring.apiinvoices.repository;

import org.springframework.data.repository.CrudRepository;
import sdv.spring.apiinvoices.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
    Optional<UnitOfMeasure> findByName(String aName);
}
