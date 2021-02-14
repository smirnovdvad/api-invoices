package sdv.spring.apiinvoices.model.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUpdateDTO extends UserViewDTO{
    private String password;
}
