package com.ginjaninja.demoRestAPI.shiftAssignment;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginjaninja.demoRestAPI.person.Person;
import com.ginjaninja.demoRestAPI.person.PersonDAOImpl;
import com.ginjaninja.demoRestAPI.shift.Shift;
import com.ginjaninja.demoRestAPI.shift.ShiftDAOImpl;

@Service
@Transactional
public class ShiftAssignmentService {
    @Autowired
    ShiftAssignmentDAOImpl shiftAssignmentDAO;
    
    @Autowired
    PersonDAOImpl personDAO;
    
    @Autowired
    ShiftDAOImpl shiftDAO;
    
    public ShiftAssignmentService(){
        
    }
    
    /**
     * Fetch ShiftAssignment with id
     * @param id    {@link Integer}
     * @return      {@link ShiftAssignment}
     */
    public ShiftAssignment get(Integer id) {
        return shiftAssignmentDAO.get(id);
    }
        
    /**
     * Set default values for shiftAssignment and save. Fills in Person and Shift objects after save so result object
     * is complete.
     * @param shiftAssignment   {@link ShiftAssignment}
     * @param checkConflict		{@link Boolean}
     * @return       			{@link ShiftAssignment}
     */
    public ShiftAssignment save(ShiftAssignment shiftAssignment, Boolean checkConflict){
    	if(checkConflict == null || !checkConflict){
    		return this.save(shiftAssignment);
    	}
    	
    	if(checkConflict && this.noConflict(shiftAssignment)){
    		return this.save(shiftAssignment);
	    }
    	return null;
    }
    
    /**
     * Saves {@link ShiftAssignment} after it's been validated
     * @param shiftAssignment	{@link ShiftAssignment}
     * @return					{@link ShiftAssignment}
     */
    public ShiftAssignment save(ShiftAssignment shiftAssignment){
    	if(this.checkMaxAssigned(shiftAssignment) && this.checkShiftPersonExists(shiftAssignment)){
	    	if(shiftAssignment.getActiveInd() == null){
		    	shiftAssignment.setActiveInd("Y");
	    	}
			if(shiftAssignment.getCreatedDtTm() == null){
				shiftAssignment.setCreatedDtTm(new Date());
			}
			this.updateActivityDtTm(shiftAssignment);
			shiftAssignmentDAO.save(shiftAssignment);
			this.fillPerson(shiftAssignment);
			this.fillShift(shiftAssignment);
			
			return shiftAssignment;
    	}else{
    		return null;
    	}
    }
    
    /**
     * Update shiftAssignment. Fill in missing fields from existing shiftAssignment object where needed.
     * Fills in Person and Shift objects after save so result object
     * is complete.
     * @param shiftAssignment    {@link ShiftAssignment}
     * @return          {@link ShiftAssignment}
     */
    public ShiftAssignment update(ShiftAssignment shiftAssignment) {
        this.fillShiftAssignment(shiftAssignment);
        System.out.println(this.checkShiftPersonExists(shiftAssignment));
        
        if(this.checkMaxAssigned(shiftAssignment) && this.checkShiftPersonExists(shiftAssignment)){
        	shiftAssignmentDAO.update(shiftAssignment);
        	this.fillPerson(shiftAssignment);
    	    this.fillShift(shiftAssignment);
    	    return shiftAssignment;
        }else{
        	return null;
        }
        
    }
    
    /**
     * Delete shiftAssignment with id
     * @param id        {@link Integer}
     */
    public Boolean delete(Integer id) {
        ShiftAssignment shiftAssignment = shiftAssignmentDAO.get(id);
        if(shiftAssignment == null){
        	return false;
        }else{
        	shiftAssignmentDAO.delete(shiftAssignment);
        	return true;
        }	
    }
    
    /**
     * Fetch all shift assignments that are active for given date range
     * @param start
     * @param end
     * @return
     */
    public Collection<ShiftAssignment> findActiveForDateRange(Date start, Date end){
    	Map<String, Object> params = new HashMap<>();
    	params.put("start", start);
    	params.put("end", end);
    	return shiftAssignmentDAO.getMany("findAllShiftAssignmentsForDateRange", params);
    }

    /**
     * Fetch all assignments for person during daterange (with overlap of start and end dates)
     * @param start		{@link Date}
     * @param end		{@link Date}
     * @param personId	{@link Integer}
     * @return			Collection<ShiftAssignment>
     */
    public Collection<ShiftAssignment> getShiftsForDateRange(Date start, Date end, Integer personId, Integer shiftId){
    	return shiftAssignmentDAO.getShiftsForDateRange(start, end, personId, shiftId);
    }
    
    
    
    
    
    //### private methods
    /**
     * Checks if person is already assigned for given day
     * @param shiftAssignment	{@link ShiftAssignment}
     * @return					{@link Boolean}
     */
    private Boolean noConflict(ShiftAssignment shiftAssignment){
    	Collection<ShiftAssignment> assignments = this.getShiftsForDateRange(shiftAssignment.getShiftDt(), 
    			shiftAssignment.getShiftDt(), shiftAssignment.getPerson().getId(), null);
    	if(assignments.isEmpty()){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    /**
     * Use existing shiftAssignment object with id to fill in missing fields for update
     * @param shiftAssignment    {@link ShiftAssignment}
     * @return         {@link ShiftAssignment}
     */
    private ShiftAssignment fillShiftAssignment(ShiftAssignment shiftAssignment){
        ShiftAssignment savedShiftAssignment = shiftAssignmentDAO.get(shiftAssignment.getId());
        if(shiftAssignment.getPerson().getId() == null){
        	shiftAssignment.setPerson(savedShiftAssignment.getPerson());
        }
        
        if(shiftAssignment.getShift().getId() == null){
            shiftAssignment.setShift(savedShiftAssignment.getShift());
        }
        if(shiftAssignment.getShiftDt() == null){
            shiftAssignment.setShiftDt(savedShiftAssignment.getShiftDt());
        }
        if(shiftAssignment.getActiveInd() == null){
            shiftAssignment.setActiveInd(savedShiftAssignment.getActiveInd());
        }
        if(shiftAssignment.getCreatedDtTm() == null){
            shiftAssignment.setCreatedDtTm(savedShiftAssignment.getCreatedDtTm());
        }
        this.updateActivityDtTm(shiftAssignment);
        return shiftAssignment;
    }
    
    /**
     * Update activityDtTm for shiftAssignment
     * @param shiftAssignment    {@link ShiftAssignment}
     * @return          {@link ShiftAssignment}
     */
    private ShiftAssignment updateActivityDtTm(ShiftAssignment shiftAssignment){
        if(shiftAssignment.getActivityDtTm() == null){
            shiftAssignment.setActivityDtTm(new Date());
        }
        return shiftAssignment;
    }
    
    /**
     * Fill in person object from id.
     * @param assignment	{@link ShiftAssignment}
     * @return				{@link ShiftAssignment}
     */
    private ShiftAssignment fillPerson(ShiftAssignment assignment){
    	if(assignment.getPerson().getId() != null){
    		assignment.setPerson(personDAO.get(assignment.getPerson().getId()));
    	}
    	return assignment;
    }
    
    /**
     * Fill in shift object from id.
     * @param assignment	{@link ShiftAssignment}
     * @return				{@link ShiftAssignment}
     */
    private ShiftAssignment fillShift(ShiftAssignment assignment){
    	if(assignment.getShift().getId() != null){
    		assignment.setShift(shiftDAO.get(assignment.getShift().getId()));
    	}
    	return assignment;
    }
    
    
    /**
     * Make sure shift.max_assigned threshold hasn't been met
     * @param shiftAssignment {@link ShiftAssignment}
     * @return					{@link Boolean}
     */
    private Boolean checkMaxAssigned(ShiftAssignment shiftAssignment){
    	Shift shift = shiftDAO.get(shiftAssignment.getShift().getId());
    	if(shift == null){
    		throw new EntityNotFoundException("Failed to load shift for shift assignment.");
    	}
    	Collection<ShiftAssignment> assignments = this.getShiftsForDateRange(shiftAssignment.getShiftDt(), 
    			shiftAssignment.getShiftDt(), null, shiftAssignment.getShift().getId());
    	if(assignments.isEmpty() || assignments.size() < shift.getMaxAssigned()){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    /**
     * Check if person and shift exist
     * @param shiftAssignment	{@link ShiftAssignment}
     * @return					{@link Boolean}
     */
    private Boolean checkShiftPersonExists(ShiftAssignment shiftAssignment){
    	Shift shift = shiftDAO.get(shiftAssignment.getShift().getId());
    	Person person = personDAO.get(shiftAssignment.getPerson().getId());
    	if(person == null || shift == null){
    		return false;
    	}else{
    		return true;
    	}
    }
}