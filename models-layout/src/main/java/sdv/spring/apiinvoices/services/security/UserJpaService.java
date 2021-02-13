package sdv.spring.apiinvoices.services.security;

import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.security.User;
import sdv.spring.apiinvoices.mapper.security.UserUpdateMapper;
import sdv.spring.apiinvoices.mapper.security.UserViewMapper;
import sdv.spring.apiinvoices.model.security.UserUpdateDTO;
import sdv.spring.apiinvoices.model.security.UserViewDTO;
import sdv.spring.apiinvoices.repository.security.UserRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserJpaService implements UserService{

    private final UserRepository userRepository;
    private final UserViewMapper userViewMapper = UserViewMapper.INSTANCE;
    private final UserUpdateMapper userUpdateMapper = UserUpdateMapper.INSTANCE;

    public UserJpaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Set<User> findAll() {
        HashSet<User> res = new HashSet<>();
        userRepository.findAll().forEach(res::add);

        return res;
    }

    @Override
    public Set<UserViewDTO> getAllUsers(){
        return findAll().stream()
                .map(userViewMapper::userToUserViewDTO).collect(Collectors.toSet());
    }

    @Override
    public UserViewDTO postUser(UserUpdateDTO userUpdateDTO) {
        return userViewMapper.userToUserViewDTO(
                userRepository.save(
                        userUpdateMapper.userUpdateDTOToUser(
                                userUpdateDTO
                        )
                )
        );
    }

    @Override
    public User findById(Integer integer) {
        return null;
    }

    @Override
    public User save(User object) {
        return null;
    }

    @Override
    public void delete(User object) {

    }

    @Override
    public void deleteById(Integer integer) {

    }
}
