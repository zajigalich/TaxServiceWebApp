package com.my.persistence.dao;

import com.my.persistence.entity.BaseEntity;

import java.util.Collection;

public interface BaseDAO <E extends BaseEntity>{
    E create(E entity);
    E update(E entity);
    boolean delete(String id);
    E findById(String id);
    Collection<E> findAll();
}
