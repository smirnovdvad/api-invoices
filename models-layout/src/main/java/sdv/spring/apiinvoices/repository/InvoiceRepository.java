package sdv.spring.apiinvoices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import sdv.spring.apiinvoices.domain.Company;
import sdv.spring.apiinvoices.domain.Invoice;
import org.springframework.data.repository.CrudRepository;
import sdv.spring.apiinvoices.model.CompanyDTO;

import java.util.Optional;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    Optional<Invoice> findByNumber(String aInvNumber);

    @Query("select a from invoices a join companies b on a.companyissuer.id = b.id" +
            " where b.tin=:#{#company.tin} and a.number = :aInvNumber and a.isReversed = :isReversed")
    Optional<Invoice> findByNumberAndCompanyIssuerAndIsReversed(@Param("aInvNumber") String aInvNumber,
                                                   @Param("company") Company company,
                                                                 @Param("isReversed") Boolean isReversed);

    Optional<Invoice> findByNumberAndIsReversed(String aInvNumber, Boolean isReversed);
}
