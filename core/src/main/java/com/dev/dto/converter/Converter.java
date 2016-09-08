package com.dev.dto.converter;

import com.dev.dto.BaseDTO;
import com.dev.entity.BaseEntity;

import java.util.List;

public interface Converter<E extends BaseEntity, D extends BaseDTO> {

    D convert(E inputObject);

    List<D> convert(List<E> inputList);

    E convertToEntity(D inputObject);

}
