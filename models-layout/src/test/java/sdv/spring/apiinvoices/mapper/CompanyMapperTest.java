package sdv.spring.apiinvoices.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sdv.spring.apiinvoices.domain.Company;
import sdv.spring.apiinvoices.initializer.TestInizializer;
import sdv.spring.apiinvoices.model.CompanyDTO;

import static org.junit.jupiter.api.Assertions.*;

class CompanyMapperTest extends TestInizializer {

    CompanyMapper companyMapper = CompanyMapper.INSTANCE;

    @BeforeEach
    public void setUp(){
        this.initCompanyIssuer();
        this.initCompanyDTO();
    }

    @Test
    void companyToCompanyDTO() {
        CompanyDTO companyDTO = companyMapper.companyToCompanyDTO(this.companyIssuer);
        assertEquals(this.companyIssuer.getTin(),companyDTO.getTin());
    }

    @Test
    void companyDtoToCompany() {
        Company company = companyMapper.companyDtoToCompany(this.companyDTO);
        assertEquals(this.companyDTO.getTin(),company.getTin());
    }
}