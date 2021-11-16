package sdv.spring.apiinvoices.services.springdatajpa;

import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.Company;
import sdv.spring.apiinvoices.repository.CompanyRepository;
import sdv.spring.apiinvoices.services.CompanyService;

import java.util.Optional;

@Service
public class CompanyJPAService extends CrudServiceCommon<Company,Long,CompanyRepository> implements CompanyService{
    CompanyJPAService(CompanyRepository companyRepository) {
        super(companyRepository);
    }

    @Override
    public Company findByTin(String aTin) {
        Optional<Company> company =
                this.getRepository().findByTin(aTin);
        if (company.isPresent())
            return company.get();
        else
            return null;
    }
}
