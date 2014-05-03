package com.ginjaninja.demoRestAPI.person;

import java.util.ArrayList;
import java.util.Collection;

public class People extends ArrayList<Person> {
	
	public People(){
		super();
	}
	
	public People(Collection<Person> persons){
		this.addAll(persons);
	}
	
}
