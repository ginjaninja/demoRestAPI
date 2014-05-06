package com.ginjaninja.demoRestAPI.shift;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ginjaninja.demoRestAPI.dao.GenericDAOImpl;

/**
 * Implements GenericDAO for ShiftAssignment class
 *
 */
@Repository
@Transactional
public class ShiftAssignmentDAOImpl extends GenericDAOImpl<ShiftAssignment> {
	
	public ShiftAssignmentDAOImpl() {
		super(ShiftAssignment.class);
	}

}