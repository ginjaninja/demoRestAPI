package com.ginjaninja.restAPI.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericDAOImpl <T, K extends Serializable> implements GenericDAO<T, K> {

	protected Class<T> entityClass;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
             .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
             .getActualTypeArguments()[1];
    }
	
	@Override
	public T get(K id) {
		return this.entityManager.find(entityClass, id);
	}
	
	@Override
	public T save(T t) {
		this.entityManager.persist(t);
        return t;
	}
	
	@Override
	public T update(T t) {
		return this.entityManager.merge(t);
	}
	
	@Override
	public void delete(T t) {
		t = this.entityManager.merge(t);
        this.entityManager.remove(t);
	}

	@Override
	public Collection<T> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
