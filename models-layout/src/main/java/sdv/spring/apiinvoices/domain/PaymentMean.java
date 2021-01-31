package sdv.spring.apiinvoices.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="paymentmeans")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentMean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "paymentmeans")
    private Set<Invoice> invoices = new HashSet<>();

    @NotEmpty
    private String description;
}
