package sdv.spring.apiinvoices.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sdv.spring.apiinvoices.domain.Company;
import sdv.spring.apiinvoices.model.CompanyDTO;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);


    CompanyDTO companyToCompanyDTO(Company company);

    Company companyDtoToCompany (CompanyDTO companyDTO);
}
