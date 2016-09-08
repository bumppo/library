package com.dev.repository;

import com.dev.entity.BookEntity;
import java.util.List;


public interface BookRepository extends AbstractRepository<BookEntity> {

    List<BookEntity> getMore(int position);

}
