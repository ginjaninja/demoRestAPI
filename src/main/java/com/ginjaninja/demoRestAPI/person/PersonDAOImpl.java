package com.ginjaninja.demoRestAPI.person;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ginjaninja.demoRestAPI.dao.GenericDAOImpl;

/**
 * Implements GenericDAO for Person class
 *
 */
@Repository
@Transactional
public class PersonDAOImpl extends GenericDAOImpl<Person> {
	
	public PersonDAOImpl() {
		super(Person.class);
	}

}
