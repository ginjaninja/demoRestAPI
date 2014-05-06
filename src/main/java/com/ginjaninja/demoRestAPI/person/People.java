package com.ginjaninja.demoRestAPI.person;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class to store ArrayList<Person> so it can be used with Message or accepted by controller.
 *
 */
public class People extends ArrayList<Person> {
	
	public People(){
		super();
	}
	
	/**
	 * Convert from Collection<Person> (ie DAO return object)
	 */
	public People(Collection<Person> persons){
		this.addAll(persons);
	}
	
}
