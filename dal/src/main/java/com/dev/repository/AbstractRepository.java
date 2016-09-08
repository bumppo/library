package com.dev.repository;

import com.dev.entity.BaseEntity;
import java.util.List;


public interface AbstractRepository<E extends BaseEntity> {

    List<E> getAll();
    E get(long id);
    void delete(long id);
    E save(E entity);
}
