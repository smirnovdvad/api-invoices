package sdv.spring.apiinvoices.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import sdv.spring.apiinvoices.domain.security.LoginFailure;
import sdv.spring.apiinvoices.domain.security.User;

import java.sql.Timestamp;
import java.util.List;

public interface LoginFailureRepository extends JpaRepository<LoginFailure, Integer> {

    List<LoginFailure> findAllByUserAndCreatedDateIsAfter(User user, Timestamp timestamp);
}
