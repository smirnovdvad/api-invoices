package sdv.spring.apiinvoices.services.springdatajpa;

import org.springframework.stereotype.Service;
import sdv.spring.apiinvoices.domain.Good;
import sdv.spring.apiinvoices.domain.UnitOfMeasure;
import sdv.spring.apiinvoices.repository.GoodRepository;
import sdv.spring.apiinvoices.services.GoodService;
import sdv.spring.apiinvoices.services.UnitOfMeasureService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class GoodJPAService implements GoodService {

    private final GoodRepository goodrepository;
    private final UnitOfMeasureService unitOfMeasureService;

    public GoodJPAService(GoodRepository goodrepository, UnitOfMeasureService unitOfMeasureService) {
        this.goodrepository = goodrepository;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @Override
    public Set<Good> findAll() {
        HashSet<Good> goods= new HashSet<>();
        goodrepository.findAll().forEach(good -> goods.add(good));
        return goods;
    }

    @Override
    public Good findById(Long aLong) {
        Optional<Good> good;
        good = goodrepository.findById(aLong);
        if (good.isPresent())
            return good.get();
        else
            return null;
    }

    @Override
    public Good save(Good object) {
        UnitOfMeasure uomTmp = unitOfMeasureService.findByName(object.getUom().getName());
        if ( uomTmp == null)
            unitOfMeasureService.save(object.getUom());
        else
            object.getUom().setId(uomTmp.getId());
        return goodrepository.save(object);
    }

    @Override
    public void delete(Good object) {
        goodrepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        goodrepository.deleteById(aLong);
    }

    @Override
    public Good findByName(String aName) {
        Optional<Good> good =
                goodrepository.findByName(aName);
        if (good.isPresent())
            return good.get();
        else
            return null;
    }
}
