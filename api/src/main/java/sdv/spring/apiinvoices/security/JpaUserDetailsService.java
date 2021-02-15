package sdv.spring.apiinvoices.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.security.Authority;
import sdv.spring.apiinvoices.domain.security.User;
import sdv.spring.apiinvoices.repository.security.UserRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //logger.info("### User " + username);
        log.info("###### User "+ username);
        User user = userRepository.findByUsername(username).orElseThrow( () ->{
           return new UsernameNotFoundException("User name: " + username + " not found");
        });

        return new org.springframework.security.core.userdetails.User( user.getUsername(),
                user.getPassword(), user.getEnabled(), user.getAccountNonExpired(),
                user.getCredentialsNonExpired(), user.getAccountNonLocked(),
                convertToSpringAuthorities(user.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> convertToSpringAuthorities(Set<Authority> authorities){
        if (authorities!=null && authorities.size()>0){
           return authorities.stream()
                    .map(Authority::getPermission)
            .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        }
        else{
            return new HashSet<>();
        }
    }
}