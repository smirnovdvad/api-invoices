package sdv.spring.apiinvoices.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sdv.spring.apiinvoices.domain.security.Authority;
import sdv.spring.apiinvoices.domain.security.Role;
import sdv.spring.apiinvoices.domain.security.User;
import sdv.spring.apiinvoices.repository.security.AuthorityRepository;
import sdv.spring.apiinvoices.repository.security.RoleRepository;
import sdv.spring.apiinvoices.repository.security.UserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
@Profile("test")
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Authority readInvoice = authorityRepository.save(Authority.builder().permission("invoice.read").build());
        Role userRole = roleRepository.save(Role.builder().name("USER").build());
        userRole.setAuthorities(new HashSet<>(Set.of(readInvoice)));
        roleRepository.saveAll(new HashSet<>(Set.of(userRole)));

        userRepository.save(User.builder()
                .username("test")
                .password(passwordEncoder.encode("test"))
                .role(userRole)
                .build());

    }
}
