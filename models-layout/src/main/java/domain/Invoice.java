package domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Invoice {

    @Id
    private Long id;
    private String Number;
}
