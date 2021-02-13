package sdv.spring.apiinvoices.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name="invoices")
@Table(name= "invoices")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="is_reversed")
    @Builder.Default
    private Boolean isReversed = Boolean.valueOf("FALSE");

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
    private Set<InvoiceLine> invoicelines;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="creation_date" , updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date updateDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="reversed_date")
    private Date reversalDate;

    @PreUpdate
    protected void onUpdate(){
        if ( isReversed != null && isReversed.booleanValue()==true
                && ( reversalDate == null || reversalDate.toString()==""))
            reversalDate = new Date();
    }

}
