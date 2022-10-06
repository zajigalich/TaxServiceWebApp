package com.my.persistence.dao;

import com.my.persistence.entity.AbstractBaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseDAO <E extends AbstractBaseEntity>{
    boolean create(E entity);
    E update(E entity);
    boolean delete(Long id);
    Optional<E> findById(Long id);
    List<E> findAll();
}
