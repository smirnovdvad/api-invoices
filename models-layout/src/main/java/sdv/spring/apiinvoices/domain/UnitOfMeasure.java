package sdv.spring.apiinvoices.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="uom")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name="uom_name")
    private String name;

    @NotEmpty
    private String description;

    @OneToMany(mappedBy = "uom")
    Set<Good> goods = new HashSet<>();

}
