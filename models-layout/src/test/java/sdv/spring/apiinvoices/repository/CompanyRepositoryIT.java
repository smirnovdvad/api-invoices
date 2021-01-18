package sdv.spring.apiinvoices.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import sdv.spring.apiinvoices.domain.Company;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class )
@DataJpaTest
public class CompanyRepositoryIT {

    @Autowired
    CompanyRepository companyRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void findByTin() {
        Optional<Company> company = companyRepository.findByTin("Tin");
        assertEquals("Tin", company.get().getTin());
    }
}