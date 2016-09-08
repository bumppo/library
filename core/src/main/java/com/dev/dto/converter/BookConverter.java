package com.dev.dto.converter;

import com.dev.dto.BookDTO;
import com.dev.entity.BookEntity;
import com.dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class BookConverter implements Converter<BookEntity, BookDTO> {

    @Autowired
    UserRepository repository;

    @Override
    public BookDTO convert(BookEntity inputObject) {
        return new BookDTO()
                .setId(inputObject.getId())
                .setName(inputObject.getName())
                .setIsbn(inputObject.getIsbn())
                .setAuthor(inputObject.getAuthor())
                .setUserName(inputObject.getUserId() == null ? null : repository.get(inputObject.getUserId()).getName());
    }

    @Override
    public List<BookDTO> convert(List<BookEntity> inputList) {
        List<BookDTO> result = new ArrayList<>();
        inputList.forEach(i -> result.add(convert(i)));
        return result;
    }

    @Override
    public BookEntity convertToEntity(BookDTO inputObject) {
        if (inputObject.getUserName().isEmpty())
            inputObject.setUserName(null);
        BookEntity entity = new BookEntity();
        entity.setId(inputObject.getId());
        entity.setName(inputObject.getName());
        entity.setIsbn(inputObject.getIsbn());
        entity.setAuthor(inputObject.getAuthor());
        entity.setUserId(inputObject.getUserName() == null ? null : repository.getByName(inputObject.getUserName()).getId());
        return entity;
    }
}
