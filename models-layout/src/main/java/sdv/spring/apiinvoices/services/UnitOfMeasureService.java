package sdv.spring.apiinvoices.services;

import sdv.spring.apiinvoices.domain.UnitOfMeasure;

public interface UnitOfMeasureService extends CrudService<UnitOfMeasure,Long>{
    UnitOfMeasure findByName(String aName);
}
