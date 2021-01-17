package sdv.spring.apiinvoices.services;

import sdv.spring.apiinvoices.domain.Good;

public interface GoodService extends CrudService<Good,Long>{
    Good findByName(String aName);
}
