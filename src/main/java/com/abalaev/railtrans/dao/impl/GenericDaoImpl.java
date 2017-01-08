package com.abalaev.railtrans.dao.impl;


import com.abalaev.railtrans.dao.api.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager em;

    public GenericDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public T create(T t) {
        this.em.persist(t);
        return t;
    }

    @Override
    public T read(PK id) {
        return this.em.find(entityClass, id);
    }

    @Override
    public T update(T t) {
        return this.em.merge(t);
    }

    @Override
    public void delete(T t) {
        t = this.em.merge(t);
        this.em.remove(t);
    }
}