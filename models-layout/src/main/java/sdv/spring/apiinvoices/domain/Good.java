package sdv.spring.apiinvoices.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="goods")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @ManyToOne
    @JoinColumn(name="uom_id")
    private UnitOfMeasure uom;

    @OneToMany(mappedBy = "good")
    private Set<InvoiceLine> invoiceLines = new HashSet<>();

}
