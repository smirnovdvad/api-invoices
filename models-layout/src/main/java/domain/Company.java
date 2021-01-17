package domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="companies")
public class Company {

    @Id
    private Long id;
    @NotNull
    @NotEmpty
    private String Name;
    @NotNull
    @NotEmpty
    private String Country;
    @NotNull
    @NotEmpty
    private String Address;
    @Email
    private String Email;
    private String Phone;
}
