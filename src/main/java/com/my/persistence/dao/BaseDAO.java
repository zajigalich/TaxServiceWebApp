package com.my.persistence.dao;

import com.my.persistence.entity.AbstractBaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface BaseDAO <E extends AbstractBaseEntity>{
    E create(E entity);
    E update(E entity);
    boolean delete(Long id);
    Optional<E> findById(Long id);
    Collection<E> findAll();
}
