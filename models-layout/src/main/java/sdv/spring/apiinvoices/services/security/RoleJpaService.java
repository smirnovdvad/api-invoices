package sdv.spring.apiinvoices.services.security;

import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.security.Role;
import sdv.spring.apiinvoices.mapper.security.RoleMapper;
import sdv.spring.apiinvoices.model.security.RoleDTO;
import sdv.spring.apiinvoices.repository.security.RoleRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleJpaService implements RoleService{

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper = RoleMapper.INSTANCE;

    public RoleJpaService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> findAll() {
        Set<Role> res = new HashSet<>();
        roleRepository.findAll().forEach(res::add);
        return res;
    }

    @Override
    public Role findById(Integer integer) {
        return null;
    }

    @Override
    public Role save(Role object) {
        return null;
    }

    @Override
    public void delete(Role object) {

    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public Set<RoleDTO> getAll() {
        return findAll().stream().map(roleMapper::roleToRoleDTO).collect(Collectors.toSet());
    }

    @Override
    public RoleDTO postRole(RoleDTO roleDTO) {
        return roleMapper.roleToRoleDTO(
                roleRepository.save(
                        roleMapper.roleDTOToRole(roleDTO)
                )
        );
    }
}
