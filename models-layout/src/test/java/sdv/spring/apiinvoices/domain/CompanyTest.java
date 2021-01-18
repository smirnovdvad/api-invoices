package sdv.spring.apiinvoices.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyTest {

    Company company;
    Long id = 1L;
    String address = "Pushkina Stree";
    String country = "Russia";
    String name = "Unit Test Company";
    String tin = "1234567";
    String phone = "12345678";
    String email = "pushkin@mail.ru";

    @BeforeEach
    public void setUp(){
        company = Company.builder()
                .id(this.id)
                .address(this.address)
                .country(this.country)
                .name(this.name)
                .tin(this.tin)
                .phone(this.phone)
                .email(this.email)
                .build();
    }

    @Test
    void getId() {
        assertEquals(this.id, company.getId());
    }

    @Test
    void getName() {
        assertEquals(this.name, company.getName());
    }

    @Test
    void getCountry() {
        assertEquals(this.country, company.getCountry());
    }

    @Test
    void getAddress() {
        assertEquals(this.address, company.getAddress());
    }

    @Test
    void getEmail() {
        assertEquals(this.email, company.getEmail());
    }

    @Test
    void getPhone() {
        assertEquals(this.phone, company.getPhone());
    }

    @Test
    void getTin() {
        assertEquals(this.tin, company.getTin());
    }
}