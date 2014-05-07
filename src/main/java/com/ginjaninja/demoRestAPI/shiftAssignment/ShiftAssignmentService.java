package com.ginjaninja.demoRestAPI.shiftAssignment;

import java.util.Date;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ShiftAssignmentService {
    @Autowired
    ShiftAssignmentDAOImpl shiftAssignmentDAO;
    
    
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
     * Set default values for shiftAssignment and save
     * @param shiftAssignment    {@link ShiftAssignment}
     * @return       			 {@link ShiftAssignment}
     */
    public ShiftAssignment save(ShiftAssignment shiftAssignment){
    	if(this.validStartEnd(shiftAssignment.getStartDtTm(), shiftAssignment.getEndDtTm())){
	        if(shiftAssignment.getActiveInd() == null){
	            shiftAssignment.setActiveInd("Y");
	        }
	        if(shiftAssignment.getCreatedDtTm() == null){
	        	shiftAssignment.setCreatedDtTm(new Date());
	        }
	        if(shiftAssignment.getCreatedDtTm() == null){
	        	shiftAssignment.setCreatedDtTm(new Date());
	        }
	        this.updateActivityDtTm(shiftAssignment);
	        return shiftAssignmentDAO.save(shiftAssignment);
    	}else{
    		return null;
    	}
    }
    
    /**
     * Update shiftAssignment. Fill in missing fields from existing shiftAssignment object where needed.
     * @param shiftAssignment    {@link ShiftAssignment}
     * @return          {@link ShiftAssignment}
     */
    public ShiftAssignment update(ShiftAssignment shiftAssignment) {
        this.fillShiftAssignment(shiftAssignment);
        if(this.validStartEnd(shiftAssignment.getStartDtTm(), shiftAssignment.getEndDtTm())){
        	return shiftAssignmentDAO.update(shiftAssignment);
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
     * Use existing shiftAssignment object with id to fill in missing fields for update
     * @param shiftAssignment    {@link ShiftAssignment}
     * @return         {@link ShiftAssignment}
     */
    private ShiftAssignment fillShiftAssignment(ShiftAssignment shiftAssignment){
        ShiftAssignment savedShiftAssignment = shiftAssignmentDAO.get(shiftAssignment.getId());
        if(shiftAssignment.getPerson() == null){
            shiftAssignment.setPerson(savedShiftAssignment.getPerson());
        }
        if(shiftAssignment.getShift() == null){
            shiftAssignment.setShift(savedShiftAssignment.getShift());
        }
        if(shiftAssignment.getStartDtTm() == null){
            shiftAssignment.setStartDtTm(savedShiftAssignment.getStartDtTm());
        }
        if(shiftAssignment.getEndDtTm() == null){
            shiftAssignment.setEndDtTm(savedShiftAssignment.getEndDtTm());
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
     * Validate start and end dates
     * @param StartDtTm	Date
     * @param endDtTm	Date
     * @return			Boolean
     */
    private Boolean validStartEnd(Date StartDtTm, Date endDtTm){
    	Boolean bool = true;
    	DateTime start = new DateTime(StartDtTm);
    	DateTime end = new DateTime(endDtTm);
    	if(start.isAfter(end) || end.isBefore(start)){
    		bool = false;
    	}
    	return bool;
    }
}