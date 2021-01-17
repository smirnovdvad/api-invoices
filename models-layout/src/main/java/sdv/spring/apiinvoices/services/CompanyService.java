package sdv.spring.apiinvoices.services;

import sdv.spring.apiinvoices.domain.Company;

public interface CompanyService extends CrudService<Company,Long>{
    Company findByTin(String aTin);
}
