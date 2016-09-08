package com.dev.repository.impl;

import com.dev.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import com.dev.repository.AbstractRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public abstract class AbstractRepositoryImpl<E extends BaseEntity> implements AbstractRepository<E> {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private BeanPropertyRowMapper<E> rowMapper;
    private SimpleJdbcInsert jdbcInsert;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    @Autowired
    public AbstractRepositoryImpl setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        return this;
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }
    @Autowired
    public AbstractRepositoryImpl setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        return this;
    }

    public BeanPropertyRowMapper<E> getRowMapper() {
        return rowMapper;
    }
    public AbstractRepositoryImpl setRowMapper(BeanPropertyRowMapper<E> rowMapper) {
        this.rowMapper = rowMapper;
        return this;
    }

    public SimpleJdbcInsert getJdbcInsert() {
        return jdbcInsert;
    }
    public AbstractRepositoryImpl setJdbcInsert(SimpleJdbcInsert jdbcInsert) {
        this.jdbcInsert = jdbcInsert;
        return this;
    }



    abstract String getTableName();

    @Override
    public List<E> getAll() {
        return jdbcTemplate.query("SELECT * FROM " + getTableName(), rowMapper);
    }

    @Override
    public E get(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM " + getTableName() + " WHERE id=?", rowMapper, id);
    }

    @Override
    @Transactional
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id=?", id);
    }

}
