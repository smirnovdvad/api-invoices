package sdv.spring.apiinvoices.services.security;

import sdv.spring.apiinvoices.domain.security.Authority;
import sdv.spring.apiinvoices.model.security.AuthorityDTO;
import sdv.spring.apiinvoices.services.CrudService;

import java.util.Set;

public interface AuthorityService extends CrudService<Authority, Integer> {
    public Set<AuthorityDTO> getAll();
    public AuthorityDTO postAuthority(AuthorityDTO authorityDTO);
    public void postAuthorirties(Set<AuthorityDTO> authoirties);
}
