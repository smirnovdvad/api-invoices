package domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name="uom")
public class UnitOfMeasure {

    @Id
    private Long id;

    @NotEmpty
    @Column(name="description")
    private String description;

}
