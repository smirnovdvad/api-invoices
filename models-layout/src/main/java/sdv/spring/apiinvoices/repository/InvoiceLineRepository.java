package sdv.spring.apiinvoices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import sdv.spring.apiinvoices.domain.InvoiceLine;

public interface InvoiceLineRepository extends CrudRepository<InvoiceLine,Long> {
}
