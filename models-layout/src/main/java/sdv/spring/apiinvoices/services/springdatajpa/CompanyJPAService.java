package sdv.spring.apiinvoices.services.springdatajpa;

import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.Company;
import sdv.spring.apiinvoices.repository.CompanyRepository;
import sdv.spring.apiinvoices.services.CompanyService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyJPAService implements CompanyService {

    private final CompanyRepository companyrepository;

    public CompanyJPAService(CompanyRepository companyrepository) {
        this.companyrepository = companyrepository;
    }

    @Override
    public Set<Company> findAll() {
        HashSet<Company> res = new HashSet<>();
        companyrepository.findAll().forEach( company -> res.add(company));
        return res;
    }

    @Override
    public Company findById(Long aLong) {
        Optional<Company> comp;
        comp = companyrepository.findById(aLong);
        if (comp.isPresent())
            return comp.get();
        else
            return null;

    }

    @Override
    public Company save(Company object) {
        return companyrepository.save(object);
    }

    @Override
    public void delete(Company object) {
        companyrepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        companyrepository.deleteById(aLong);
    }

    @Override
    public Company findByTin(String aTin) {
        Optional<Company> company =
                companyrepository.findByTin(aTin);
        if (company.isPresent())
            return company.get();
        else
            return null;
    }
}
