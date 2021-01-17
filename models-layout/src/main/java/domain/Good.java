package domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="goods")
@Data
public class Good {

    @Id
    private Long id;

    @Column(name = "name")
    @NotEmpty
    private String name;
}
