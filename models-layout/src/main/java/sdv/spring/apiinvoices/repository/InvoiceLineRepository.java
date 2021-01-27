package sdv.spring.apiinvoices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import sdv.spring.apiinvoices.domain.Invoice;
import sdv.spring.apiinvoices.domain.InvoiceLine;

import java.util.Optional;

public interface InvoiceLineRepository extends CrudRepository<InvoiceLine,Long> {
    Optional<InvoiceLine> findInvoiceLineByLinenumberAndInvoice(String lineNumber, Invoice invoice);
}
