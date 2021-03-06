package com.ginjaninja.demoRestAPI.shift;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ginjaninja.demoRestAPI.dao.GenericDAOImpl;

/**
 * Implements GenericDAO for Shift class
 *
 */

@Repository
@Transactional
public class ShiftDAOImpl extends GenericDAOImpl<Shift> {
	
	public ShiftDAOImpl() {
		super(Shift.class);
	}

}
