package sdv.spring.apiinvoices.mapper.security;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sdv.spring.apiinvoices.domain.security.Authority;
import sdv.spring.apiinvoices.model.security.AuthorityDTO;

@Mapper
public interface AuthorityMapper {

    AuthorityMapper INSTANCE = Mappers.getMapper(AuthorityMapper.class);

    AuthorityDTO authorirtyToAuthorityDTO(Authority authority);

    Authority AuthorityDTOToAuthority(AuthorityDTO authorityDTO);

}
