package com.ginjaninja.demoRestAPI.shiftAssignment;

import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	@PersistenceContext
	private EntityManager entityManager;
	
	public ShiftAssignmentDAOImpl() {
		super(ShiftAssignment.class);
	}

	@SuppressWarnings("unchecked")
	public Collection<ShiftAssignment> getShiftsForDateRange(Date start, Date end, Integer personId, Integer shiftId){
		String query="SELECT a FROM ShiftAssignment a "
	    		+ "INNER join a.shift s "
	    		+ "INNER join a.person p "
	    		+ "WHERE :start <= a.shiftDt AND :end >= a.shiftDt "
	    		+ "AND a.activeInd = 'Y' "
	    		+ "AND (s.id = :shiftId OR p.id = :personId)";
		
		return entityManager.createQuery(query).setParameter("shiftId", shiftId).setParameter("personId", personId)
				.setParameter("start", start).setParameter("end", end).getResultList();
	}
}