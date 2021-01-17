package sdv.spring.apiinvoices.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="goods")
@Data
@Builder
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @OneToOne
    @JoinColumn(name="uom_id")
    private UnitOfMeasure uom;

//    @OneToOne
//    private InvoiceLine invoiceLine;
}
