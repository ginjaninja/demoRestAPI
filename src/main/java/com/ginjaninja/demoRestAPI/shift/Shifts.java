package com.ginjaninja.demoRestAPI.shift;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class to store ArrayList<Shift> so it can be used with Message or accepted by controller.
 *
 */
public class Shifts extends ArrayList<Shift>{

	public Shifts(){
		super();
	}
	
	/**
	 * Convert from Collection<Person> (ie DAO return object)
	 */
	public Shifts(Collection<Shift> cShift){
		this.addAll(cShift);
	}
}
