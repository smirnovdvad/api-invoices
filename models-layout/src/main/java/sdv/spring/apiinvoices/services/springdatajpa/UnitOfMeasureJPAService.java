package sdv.spring.apiinvoices.services.springdatajpa;

import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.UnitOfMeasure;
import sdv.spring.apiinvoices.repository.UnitOfMeasureRepository;
import sdv.spring.apiinvoices.services.UnitOfMeasureService;

import java.util.Optional;
import java.util.Set;

@Service
public class UnitOfMeasureJPAService implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureJPAService(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Set<UnitOfMeasure> findAll() {
        return null;
    }

    @Override
    public UnitOfMeasure findById(Long aLong) {
        Optional<UnitOfMeasure> unitOfMeasure;
        unitOfMeasure = unitOfMeasureRepository.findById(aLong);
        if (unitOfMeasure.isPresent())
            return unitOfMeasure.get();
        else
            return null;
    }

    @Override
    public UnitOfMeasure save(UnitOfMeasure object) {
        return unitOfMeasureRepository.save(object);
    }

    @Override
    public void delete(UnitOfMeasure object) {
        unitOfMeasureRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        unitOfMeasureRepository.deleteById(aLong);
    }

    @Override
    public UnitOfMeasure findByName(String aName) {
        Optional<UnitOfMeasure> unitOfMeasure =
                unitOfMeasureRepository.findByName(aName);
        if (unitOfMeasure.isPresent())
            return unitOfMeasure.get();
        else
            return null;
    }
}
