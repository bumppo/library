package com.dev.service.impl;

import com.dev.dto.BaseDTO;
import com.dev.dto.converter.Converter;
import com.dev.entity.BaseEntity;
import com.dev.repository.AbstractRepository;
import com.dev.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public abstract class AbstractServiceImpl<R extends AbstractRepository<E>, E extends BaseEntity, D extends BaseDTO> implements AbstractService<D> {

    private R repository;
    private Converter<E, D> converter;

    public R getRepository() {
        return repository;
    }
    @Autowired
    public void setRepository(R repository) {
        this.repository = repository;
    }

    public Converter<E, D> getConverter() {
        return converter;
    }
    @Autowired
    public void setConverter(Converter<E, D> converter) {
        this.converter = converter;
    }



    @Override
    public List<D> getAll() {
        return converter.convert(repository.getAll());
    }

    @Override
    public D get(long id) {
        return converter.convert(repository.get(id));
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    public D save(D dto) {
        return converter.convert(repository.save(converter.convertToEntity(dto)));
    }

}
