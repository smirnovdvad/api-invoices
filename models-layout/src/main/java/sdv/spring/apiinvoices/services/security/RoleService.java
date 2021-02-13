package sdv.spring.apiinvoices.services.security;

import sdv.spring.apiinvoices.domain.security.Role;
import sdv.spring.apiinvoices.model.security.RoleDTO;
import sdv.spring.apiinvoices.services.CrudService;

import java.util.Set;

public interface RoleService extends CrudService<Role,Integer> {
    public Set<RoleDTO> getAll();
    public RoleDTO postRole(RoleDTO roleDTO);
}
