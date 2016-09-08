package com.dev.repository;

import com.dev.entity.UserEntity;


public interface UserRepository extends AbstractRepository<UserEntity> {

    UserEntity getByName(String name);

}
