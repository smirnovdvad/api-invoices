package sdv.spring.apiinvoices.services.springdatajpa;

import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.UnitOfMeasure;
import sdv.spring.apiinvoices.repository.UnitOfMeasureRepository;
import sdv.spring.apiinvoices.services.UnitOfMeasureService;

import java.util.Optional;

@Service
public class UnitOfMeasureJPAService extends CrudServiceCommon<UnitOfMeasure,Long,UnitOfMeasureRepository>
        implements UnitOfMeasureService {

    UnitOfMeasureJPAService(UnitOfMeasureRepository unitOfMeasureRepository){
        super(unitOfMeasureRepository);
    }

    @Override
    public UnitOfMeasure findByName(String aName) {
        Optional<UnitOfMeasure> unitOfMeasure =
                this.getRepository().findByName(aName);
        if (unitOfMeasure.isPresent())
            return unitOfMeasure.get();
        else
            return null;
    }
}
