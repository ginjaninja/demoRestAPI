package com.ginjaninja.restAPI.dao;

import java.io.Serializable;


public interface GenericDAO<T, K extends Serializable> {

	public T get(K id);
	
	public T save(T t);
	
	public T update(T t);
	
	public void delete(T t);
	
	public void delete(K id);
}
