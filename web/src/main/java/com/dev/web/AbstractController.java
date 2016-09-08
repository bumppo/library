package com.dev.web;

import com.dev.dto.BaseDTO;
import com.dev.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;


public abstract class AbstractController<S extends AbstractService<D>, D extends BaseDTO> {
    
    private S service;

    public S getService() {
        return service;
    }

    @Autowired
    public AbstractController setService(S service) {
        this.service = service;
        return this;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<D> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public D get(@PathVariable long id){
        return service.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable long id){
        service.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public D save(@Valid D D){
        return service.save(D);
    }

}
