package sdv.spring.apiinvoices.repository;

import sdv.spring.apiinvoices.domain.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
