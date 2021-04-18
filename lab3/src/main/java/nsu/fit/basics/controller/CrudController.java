package nsu.fit.basics.controller;

import lombok.RequiredArgsConstructor;
import nsu.fit.basics.service.CrudService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
public class CrudController<T, ID> {
    private final CrudService<T, ID> service;

    @PostMapping("/add")
    public T add(@RequestBody T entity) {
        return service.addEntity(entity);
    }

    @PutMapping("/update")
    public T update(@RequestBody T entity) {
        return service.updateEntity(entity);
    }

    @PostMapping("/delete")
    public void deleteById(@RequestParam ID id) {
        service.deleteById(id);
    }

    @GetMapping("/by-id")
    public T getById(@RequestParam ID id) {
        return service.getById(id);
    }
}
