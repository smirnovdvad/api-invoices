package domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
@Table(name="invoice_lines")
public class InvoiceLine {

    @Id
    private Long id;

    @NotEmpty
    @Column(name="invoice_id")
    private Long invoiceId;

    @NotEmpty
    @Column(name = "line_number")
    private String lineNumber;

    @OneToMany
    private Set<Good> goods;

    @OneToOne
    private UnitOfMeasure uom;


}
