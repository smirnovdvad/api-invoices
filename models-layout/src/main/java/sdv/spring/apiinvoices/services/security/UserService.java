package sdv.spring.apiinvoices.services.security;

import sdv.spring.apiinvoices.domain.security.User;
import sdv.spring.apiinvoices.model.security.UserUpdateDTO;
import sdv.spring.apiinvoices.model.security.UserViewDTO;
import sdv.spring.apiinvoices.services.CrudService;

import java.util.Set;

public interface UserService extends CrudService<User,Integer> {

    public Set<UserViewDTO> getAllUsers();
    public UserViewDTO postUser(UserUpdateDTO userUpdateDTO);
}
