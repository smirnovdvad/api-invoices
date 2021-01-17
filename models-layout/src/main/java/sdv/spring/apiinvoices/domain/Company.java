package sdv.spring.apiinvoices.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="companies")
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String country;
    @NotNull
    @NotEmpty
    private String address;
    @Email
    private String email;

    private String phone;

    @NotEmpty
    private String tin;
}
