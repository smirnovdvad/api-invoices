package sdv.spring.apiinvoices.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name= "invoices")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String number;
    @Column(name = "issue_date")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "company_issuer_id")
    private Company companyissuer;
    @ManyToOne
    @JoinColumn(name = "company_receiver_id")
    private Company companyreceiver;

    @ManyToMany
    @JoinTable(name = "invoices_paymentmeans" , joinColumns = @JoinColumn(name = "invoice_id"),
               inverseJoinColumns = @JoinColumn(name="paymentmean_id"))
    private Set<PaymentMean> paymentmeans;

    @OneToMany(mappedBy = "invoice" , cascade =  CascadeType.ALL)
    //@JoinColumn(name="id", nullable = false, insertable = false, updatable = false)
    private Set<InvoiceLine> invoicelines;

}
