package sdv.spring.apiinvoices.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import sdv.spring.apiinvoices.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
