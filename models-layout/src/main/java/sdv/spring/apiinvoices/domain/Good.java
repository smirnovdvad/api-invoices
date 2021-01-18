package sdv.spring.apiinvoices.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="goods")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @OneToOne
    @JoinColumn(name="uom_id")
    private UnitOfMeasure uom;

}
