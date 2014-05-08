package com.ginjaninja.demoRestAPI.shiftAssignment;

import java.util.ArrayList;
import java.util.Collection;

public class ShiftAssignmentList extends ArrayList<ShiftAssignment> {

	public ShiftAssignmentList(){
		super();
	}
	
	/**
	 * Convert from Collection<Person> (ie DAO return object)
	 */
	public ShiftAssignmentList(Collection<ShiftAssignment> cAssignment){
		this.addAll(cAssignment);
	}
}
