package sdv.spring.apiinvoices.domain;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name="paymentmeans")
@Builder
public class PaymentMean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String description;
}
