package sdv.spring.apiinvoices.services.springdatajpa;

import lombok.Getter;
import org.springframework.data.repository.CrudRepository;
import sdv.spring.apiinvoices.services.CrudService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CrudServiceCommon<T,ID, Rep extends CrudRepository<T,ID>> implements CrudService<T, ID> {

    @Getter
    private final Rep repository;
    CrudServiceCommon(Rep repository){
        this.repository = repository;
    }

    @Override
    public Set<T> findAll() {
        HashSet<T> res = new HashSet<>();
        repository.findAll().forEach(item->res.add(item));

        return res;
    }

    @Override
    public T findById(ID id) {
        Optional<T> resOpt;
        resOpt = repository.findById(id);
        if (resOpt.isPresent()){
            return resOpt.get();
        }else {
            return null;
        }
    }

    @Override
    public T save(T object) {
        return repository.save(object);
    }

    @Override
    public void delete(T object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
