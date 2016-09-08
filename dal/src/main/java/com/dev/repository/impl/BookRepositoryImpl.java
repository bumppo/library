package com.dev.repository.impl;

import com.dev.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import com.dev.repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public class BookRepositoryImpl extends AbstractRepositoryImpl<BookEntity> implements BookRepository {

    private static final Integer COUNT_PER_PAGE = 5;

    @Autowired
    public BookRepositoryImpl(@Qualifier("bookRowMapper") BeanPropertyRowMapper<BookEntity> rowMapper,
                              @Qualifier("insertBook") SimpleJdbcInsert insertBook) {
        this.setRowMapper(rowMapper).setJdbcInsert(insertBook);
    }

    @Override
    String getTableName() {
        return "books";
    }

    @Override
    @Transactional
    public BookEntity save(BookEntity bookEntity) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(bookEntity);
        if (bookEntity.getId() == null){
            Number newKey = getJdbcInsert().executeAndReturnKey(parameterSource);
            bookEntity.setId(newKey.longValue());
        } else {
            getNamedParameterJdbcTemplate().update("UPDATE books SET name=:name, isbn=:isbn, author=:author, user_id=:userId  WHERE id=:id", parameterSource);
        }
        return bookEntity;
    }

    @Override
    public List<BookEntity> getMore(int position) {
        return getJdbcTemplate().query("SELECT * FROM books ORDER BY author LIMIT ?", getRowMapper(), position + COUNT_PER_PAGE);
    }
}
