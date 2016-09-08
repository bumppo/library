package com.dev.entity;


public abstract class NamedEntity extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public NamedEntity setName(String name) {
        this.name = name;
        return this;
    }
}
