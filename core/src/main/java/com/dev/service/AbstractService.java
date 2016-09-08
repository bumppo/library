package com.dev.service;

import com.dev.dto.BaseDTO;

import java.util.List;


public interface AbstractService<D extends BaseDTO> {
    List<D> getAll();
    D get(long id);
    void delete(long id);
    D save(D dto);
}
