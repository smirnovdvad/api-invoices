package domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name= "invoices")
public class Invoice {

    @Id
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "issue_date")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "company_issuer_id")
    private Company companyIssuer;
    @ManyToOne
    @JoinColumn(name = "company_receiver_id")
    private Company companyReceiver;

    @Column(name="paymentmeans")
    @ManyToMany
    @JoinTable(name = "invoices_paymentmeans" , joinColumns = @JoinColumn(name = "invoice_id"),
               inverseJoinColumns = @JoinColumn(name="paymentmean_id"))
    private Set<PaymentMean> paymentmeans;

}
