package sdv.spring.apiinvoices.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import sdv.spring.apiinvoices.domain.security.LoginSuccess;

public interface LoginSuccessRepository extends JpaRepository<LoginSuccess, Integer> {
}
