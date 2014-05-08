package com.ginjaninja.demoRestAPI.shift;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class to store ArrayList<Shift> so it can be used with Message or accepted by controller.
 *
 */
public class ShiftList extends ArrayList<Shift>{

	public ShiftList(){
		super();
	}
	
	/**
	 * Convert from Collection<Person> (ie DAO return object)
	 */
	public ShiftList(Collection<Shift> cShift){
		this.addAll(cShift);
	}
}
