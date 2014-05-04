package com.ginjaninja.demoRestAPI.person;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	@Autowired
	PersonDAOImpl personDAO;
	//GenericDAO<Person, Long> personDAO;
	
	public PersonService(){
		
	}
	
	/**
	 * Fetch Person with id
	 * @param id	{@link Long}
	 * @return		{@link Person}
	 */
	public Person get(Long id) {
		return personDAO.get(id);
	}
	
	/**
	 * Fetch all from Person table
	 * @return		{@link Collection}
	 */
	public Collection<Person> getAll() {
		return personDAO.getAll();
	}
	
	/**
	 * Set default values for person and save
	 * @param person	{@link Person}
	 * @return			{@link Person}
	 */
	public Person save(Person person){
		if(person.getActiveInd() == null){
			person.setActiveInd("Y");
		}
		this.updateActivityDtTm(person);
		return personDAO.save(person);
	}
	
	/**
	 * Update person. Fill in missing members from existing person object where needed.
	 * @param person	{@link Person}
	 * @return			{@link Person}
	 */
	public Person update(Person person) {
		this.fillPerson(person);
		return personDAO.update(person);
	}
	
	/**
	 * Delete person with id
	 * @param id		{@link Long}
	 */
	public void delete(Long id) {
		Person person = personDAO.get(id);
		personDAO.delete(person);
	}
	
	/**
	 * Use existing person object to fill in missing members
	 * @param person	{@link Person}
	 * @return			{@link Person}
	 */
	private Person fillPerson(Person person){
		Person savedPerson = personDAO.get(person.getId());
		if(person.getActiveInd() == null){
			person.setActiveInd(savedPerson.getActiveInd());
		}
		if(person.getFirstName() == null){
			person.setFirstName(savedPerson.getFirstName());
		}
		if(person.getLastName() == null){
			person.setLastName(savedPerson.getLastName());
		}
		this.updateActivityDtTm(person);
		return person;
	}
	
	/**
	 * Update activityDtTm for person
	 * @param person	{@link Person}
	 * @return			{@link Person}
	 */
	private Person updateActivityDtTm(Person person){
		if(person.getActivityDtTm() == null){
			person.setActivityDtTm(new Date());
		}
		return person;
	}
}
