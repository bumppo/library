package com.dev.repository.impl;

import com.dev.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${books.table}") private String tableName;

    @Autowired
    public BookRepositoryImpl(@Qualifier("bookRowMapper") BeanPropertyRowMapper<BookEntity> rowMapper,
                              @Qualifier("insertBook") SimpleJdbcInsert insertBook) {
        this.setRowMapper(rowMapper).setJdbcInsert(insertBook);
    }

    @Override
    String getTableName() {
        return tableName;
    }

    @Override
    @Transactional
    public BookEntity save(BookEntity bookEntity) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(bookEntity);
        if (bookEntity.getId() == null){
            Number newKey = getJdbcInsert().executeAndReturnKey(parameterSource);
            bookEntity.setId(newKey.longValue());
        } else {
            String sql = "UPDATE books SET name=:name, isbn=:isbn, author=:author, user_id=:userId  WHERE id=:id";
            getNamedParameterJdbcTemplate().update(sql, parameterSource);
        }
        return bookEntity;
    }

    @Override
    public List<BookEntity> getMore(int position) {
        String sql = SELECT + getTableName() + " ORDER BY author LIMIT ?";
        return getJdbcTemplate().query(sql, getRowMapper(), position + COUNT_PER_PAGE);
    }
}
