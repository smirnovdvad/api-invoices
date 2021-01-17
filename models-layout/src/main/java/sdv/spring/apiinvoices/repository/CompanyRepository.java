package sdv.spring.apiinvoices.repository;

import org.springframework.data.repository.CrudRepository;
import sdv.spring.apiinvoices.domain.Company;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company,Long> {
    Optional<Company> findByTin(String aTin);
}
