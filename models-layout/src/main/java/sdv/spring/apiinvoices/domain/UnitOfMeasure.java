package sdv.spring.apiinvoices.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="uom")
@Builder
@Data
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name="uom_name")
    private String name;

    @NotEmpty
    private String description;

//    @OneToOne
//    private Good good;

}
