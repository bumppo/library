package com.dev.service;

import com.dev.dto.BookDTO;
import java.util.List;


public interface BookService extends AbstractService<BookDTO> {

    List<BookDTO> getMore(int position);
}
