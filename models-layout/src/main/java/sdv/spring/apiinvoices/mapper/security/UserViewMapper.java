package sdv.spring.apiinvoices.mapper.security;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import sdv.spring.apiinvoices.domain.security.User;
import sdv.spring.apiinvoices.model.security.UserViewDTO;

@Mapper
public interface UserViewMapper {

    UserViewMapper INSTANCE = Mappers.getMapper(UserViewMapper.class);

    @Mapping(source = "accountNonExpired", target = "accountIsExpired", qualifiedByName = "accountExpiration")
    @Mapping(source = "accountNonLocked", target = "accountIsLocked", qualifiedByName = "accountLocked")
    @Mapping(source = "credentialsNonExpired", target = "credentialsIsExpired", qualifiedByName = "credentialsExpiration")
    UserViewDTO userToUserViewDTO(User user);

    @Mapping(source = "accountIsExpired", target = "accountNonExpired", qualifiedByName = "accountExpiration")
    @Mapping(source = "accountIsLocked", target = "accountNonLocked", qualifiedByName = "accountLocked")
    @Mapping(source = "credentialsIsExpired", target = "credentialsNonExpired", qualifiedByName = "credentialsExpiration")
    User userViewDTOToUser(UserViewDTO userViewDTO);

    @Named("accountExpiration")
    static Boolean accountExpiration(Boolean accountExpiration){
        return !accountExpiration;
    }

    @Named("accountLocked")
    static Boolean accountBlock(Boolean accountBlock){
        return !accountBlock;
    }

    @Named("credentialsExpiration")
    static Boolean credentialsExpiration(Boolean credentialsExpiration){
        return !credentialsExpiration;
    }

}
