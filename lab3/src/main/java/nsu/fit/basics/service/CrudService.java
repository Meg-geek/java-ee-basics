package nsu.fit.basics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
public class CrudService<T, ID> {
    private final JpaRepository<T, ID> repository;

    public T addEntity(T entity) {
        return repository.save(entity);
    }

    public T updateEntity(T entity) {
        return repository.save(entity);
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    public T getById(ID id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Entity id " + id + " doesn't exist"));
    }

    public List<T> saveAll(List<T> entities) {
        return repository.saveAll(entities);
    }

    protected JpaRepository<T, ID> getRepository() {
        return repository;
    }
}
