package com.dev.entity;

public class UserEntity extends NamedEntity {

    private String password;

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
}
