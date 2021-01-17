package sdv.spring.apiinvoices.repository;

import org.springframework.data.repository.CrudRepository;
import sdv.spring.apiinvoices.domain.Good;

import java.util.Optional;

public interface GoodRepository extends CrudRepository<Good,Long> {
    Optional<Good> findByName(String aName);
}
