package com.ginjaninja.restAPI.dao;

import java.io.Serializable;
import java.util.Collection;


public interface GenericDAO<T, K extends Serializable> {

	public T get(K id);
	
	public Collection<T> getAll();
	
	public T save(T t);
	
	public T update(T t);
	
	public void delete(T t);
}
