package com.ginjaninja.demoRestAPI.dao;

import java.util.Collection;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


public abstract class GenericDAOImpl <T>{

	protected Class<T> entityClass;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GenericDAOImpl(Class entityClass){
		this.entityClass = entityClass;
	}
	
	public T get(Integer id) {
		return this.entityManager.find(entityClass, id);
	}
	
	
	public T save(T t) {
		this.entityManager.persist(t);
        return t;
	}
	
	
	public T update(T t) {
		return this.entityManager.merge(t);
	}
	
	
	public void delete(T t) {
		t = this.entityManager.merge(t);
        this.entityManager.remove(t);
	}

	
	@SuppressWarnings("unchecked")
	public Collection<T> getMany(String queryName, Map<String, Object> params) {
		Query query = entityManager.createNamedQuery(queryName);
		if(params != null){
			for(Map.Entry<String, Object> param : params.entrySet()){
				query.setParameter(param.getKey(), param.getValue());
			}
		}
		return query.getResultList();
	}

	public Collection<T> getMany(String queryName) {
		return this.getMany(queryName, null);
	}
}
