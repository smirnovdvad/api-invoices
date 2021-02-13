package sdv.spring.apiinvoices.services.security;

import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.security.Authority;
import sdv.spring.apiinvoices.mapper.security.AuthorityMapper;
import sdv.spring.apiinvoices.model.security.AuthorityDTO;
import sdv.spring.apiinvoices.repository.security.AuthorityRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorityJpaService implements AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper = AuthorityMapper.INSTANCE;

    public AuthorityJpaService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Set<Authority> findAll() {
        Set<Authority> res = new HashSet<>();
        authorityRepository.findAll().forEach(authority -> {
            res.add(authority);
        });

        return res;
    }

    @Override
    public Authority findById(Integer integer) {
        return null;
    }

    @Override
    public Authority save(Authority object) {
        return authorityRepository.save(object);
    }

    @Override
    public void delete(Authority object) {

    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public Set<AuthorityDTO> getAll() {
        return findAll().stream().map(authorityMapper::authorirtyToAuthorityDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public AuthorityDTO postAuthority(AuthorityDTO authorityDTO) {
        return authorityMapper.authorirtyToAuthorityDTO(
                authorityRepository.save(authorityMapper.AuthorityDTOToAuthority(authorityDTO))
        );
    }

    @Override
    public void postAuthorirties(Set<AuthorityDTO> authoirties) {
        authoirties.stream().map(
                authorityMapper::AuthorityDTOToAuthority
        ).forEach(
                authorityRepository::save
        );
    }
}
