package domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name="paymentmeans")
public class PaymentMean {

    @Id
    private Long id;

    @Column(name="description")
    @NotEmpty
    private String description;
}
