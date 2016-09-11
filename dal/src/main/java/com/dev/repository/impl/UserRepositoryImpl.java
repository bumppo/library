package com.dev.repository.impl;

import com.dev.entity.UserEntity;
import com.dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl extends AbstractRepositoryImpl<UserEntity> implements UserRepository {

    @Value("${users.table}") private String tableName;

    @Autowired
    public UserRepositoryImpl(@Qualifier("userRowMapper") BeanPropertyRowMapper<UserEntity> rowMapper,
                              @Qualifier("insertUser") SimpleJdbcInsert insertUser) {
        this.setRowMapper(rowMapper).setJdbcInsert(insertUser);
    }

    @Override
    String getTableName() {
        return tableName;
    }

    @Override
    @Transactional
    public UserEntity save(UserEntity userEntity) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(userEntity);
        if (userEntity.getId() == null){
            Number newKey = getJdbcInsert().executeAndReturnKey(parameterSource);
            userEntity.setId(newKey.longValue());
        } else {
            String sql = "UPDATE users SET name=:name, password=:password WHERE id=:id";
            getNamedParameterJdbcTemplate().update(sql, parameterSource);
        }
        return userEntity;
    }

    @Override
    public UserEntity getByName(String name) {
        String sql = SELECT + getTableName() + " WHERE name=?";
        return getJdbcTemplate().queryForObject(sql, getRowMapper(), name);
    }
}
