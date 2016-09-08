package com.dev.service.impl;

import com.dev.dto.BookDTO;
import com.dev.entity.BookEntity;
import com.dev.repository.BookRepository;
import org.springframework.stereotype.Service;
import com.dev.service.BookService;

import java.util.List;


@Service
public class BookServiceImpl extends AbstractServiceImpl<BookRepository, BookEntity, BookDTO> implements BookService {

    @Override
    public List<BookDTO> getMore(int position) {
        return getConverter().convert(getRepository().getMore(position));
    }
}
