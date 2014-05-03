package com.ginjaninja.demoRestAPI.person;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PersonDAOImpl {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Person get(Long id) {
		return this.entityManager.find(Person.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Person> getAll() {
		Query query = entityManager.createQuery("SELECT p from Person p");
		return query.getResultList();
	}
	
	public Person save(Person person) {
		this.entityManager.persist(person);
        return person;
	}
	
	public Person update(Person person) {
		return this.entityManager.merge(person);
	}
	
	public void delete(Person person) {
		person = this.entityManager.merge(person);
        this.entityManager.remove(person);
	}
	
	public void delete(Long id) {
		Person person = entityManager.find(Person.class, id);
		this.entityManager.remove(person);
	}
}
