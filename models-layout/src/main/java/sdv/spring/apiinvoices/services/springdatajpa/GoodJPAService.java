package sdv.spring.apiinvoices.services.springdatajpa;

import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.Good;
import sdv.spring.apiinvoices.domain.UnitOfMeasure;
import sdv.spring.apiinvoices.repository.GoodRepository;
import sdv.spring.apiinvoices.services.GoodService;
import sdv.spring.apiinvoices.services.UnitOfMeasureService;

import java.util.Optional;

@Service
public class GoodJPAService extends CrudServiceCommon<Good,Long,GoodRepository> implements GoodService {

    private final UnitOfMeasureService unitOfMeasureService;
    GoodJPAService(GoodRepository goodRepository,UnitOfMeasureService aUnitOfMeasureService){
        super(goodRepository);
        unitOfMeasureService = aUnitOfMeasureService;
    }

    @Override
    public Good save(Good object) {
        UnitOfMeasure uomTmp = unitOfMeasureService.findByName(object.getUom().getName());
        if ( uomTmp != null)
            object.getUom().setId(uomTmp.getId());
        else
            unitOfMeasureService.save(object.getUom());
        return this.getRepository().save(object);
    }

    @Override
    public Good findByName(String aName) {
        Optional<Good> good =
                this.getRepository().findByName(aName);
        if (good.isPresent())
            return good.get();
        else
            return null;
    }
}
