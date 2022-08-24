package com.my.persistence.entity;

abstract class AbstractEntityBuilder <E extends AbstractBaseEntity> {

    protected E entity = null;

    public <F extends AbstractEntityBuilder<E>> F id(Long id){
        this.entity.id = id;
        return null;
    }
}