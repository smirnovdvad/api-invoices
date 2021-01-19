package sdv.spring.apiinvoices.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sdv.spring.apiinvoices.domain.Company;
import sdv.spring.apiinvoices.repository.CompanyRepository;
import sdv.spring.apiinvoices.services.CompanyService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


class CompanyJPAServiceIT {

    CompanyService companyService;

    Set<Company> companies = new HashSet<>();
    Company company;
    Company company2;

    @Mock
    CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        companyService = new CompanyJPAService(companyRepository);

        company = Company.builder()
                .id(1L)
                .address("Address")
                .country("Country")
                .name("Name")
                .tin("1234567")
                .phone("Phone")
                .email("email@mail.ru")
                .build();
        companies.add(company);

        company2 = Company.builder()
                .id(2L)
                .address("Address 2")
                .country("Country 2")
                .name("Name 2")
                .tin("12345678")
                .phone("Phone 2")
                .email("email2@mail.ru")
                .build();
        companies.add(company2);
    }

    @Test
    void findAll() {
        when(companyRepository.findAll()).thenReturn(companies);
        HashSet<Company> companiesRes = (HashSet<Company>) companyService.findAll();
        assertEquals(2,companiesRes.size());
    }

    @Test
    void findById() {
        Optional<Company> optCompany = Optional.of(company);
        when(companyRepository.findById(anyLong())).thenReturn(optCompany);
        assertEquals("1234567",companyService.findById(1L).getTin());
        verify(companyRepository, times(1)).findById(anyLong());
        verify(companyRepository,never()).findAll();
    }

    @Test
    void save() {
        when(companyRepository.save(any())).thenReturn(company2);
        assertEquals(2L,companyService.save(company).getId());
    }

    @Test
    void delete() {
        companyService.delete(company);
        verify(companyRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        companyService.deleteById(company2.getId());
        verify(companyRepository,times(1)).deleteById(2L);
        verify(companyRepository,never()).delete(any());

    }

    @Test
    void findByTin() {
        Optional<Company> optCompany = Optional.of(company2);
        when(companyRepository.findByTin(anyString())).thenReturn(optCompany);
        assertEquals(2L,companyService.findByTin("Tin").getId());
    }

    @Test
    void notFindByTin(){
        assertEquals(null,companyService.findByTin("Tin"));
    }
}