package com.my.persistence.entity;

public abstract class AbstractBaseEntity {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}