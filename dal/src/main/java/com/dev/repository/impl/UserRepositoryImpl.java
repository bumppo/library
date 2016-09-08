package com.dev.repository.impl;

import com.dev.entity.UserEntity;
import com.dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl extends AbstractRepositoryImpl<UserEntity> implements UserRepository {

    @Autowired
    public UserRepositoryImpl(@Qualifier("userRowMapper") BeanPropertyRowMapper<UserEntity> rowMapper,
                              @Qualifier("insertUser") SimpleJdbcInsert insertUser) {
        this.setRowMapper(rowMapper).setJdbcInsert(insertUser);
    }

    @Override
    String getTableName() {
        return "users";
    }

    @Override
    @Transactional
    public UserEntity save(UserEntity userEntity) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(userEntity);
        if (userEntity.getId() == null){
            Number newKey = getJdbcInsert().executeAndReturnKey(parameterSource);
            userEntity.setId(newKey.longValue());
        } else {
            getNamedParameterJdbcTemplate().update("UPDATE users SET name=:name, password=:password WHERE id=:id", parameterSource);
        }
        return userEntity;
    }

    @Override
    public UserEntity getByName(String name) {
        return getJdbcTemplate().queryForObject("SELECT * FROM users WHERE name=?", getRowMapper(), name);
    }
}
