package com.ginjaninja.demoRestAPI.shift;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ShiftService {
    @Autowired
    ShiftDAOImpl shiftDAO;
    
    
    public ShiftService(){
        
    }
    
    /**
     * Fetch Shift with id
     * @param id    {@link Integer}
     * @return      {@link Shift}
     */
    public Shift get(Integer id) {
        return shiftDAO.get(id);
    }
    
    /**
     * Fetch all from Shift table
     * @return      {@link Collection}
     */
    public Collection<Shift> getAll() {
        return shiftDAO.getMany("getAllShifts");
    }
    
    /**
     * Set default values for shift and save
     * @param shift    {@link Shift}
     * @return          {@link Shift}
     */
    public Shift save(Shift shift){
    	if(shift.getActiveInd() == null){
	    	shift.setActiveInd("Y");
	    }
	    if(shift.getMaxAssigned() == null){
	    	shift.setMaxAssigned(1);
	    }
	    if(shift.getMinAssigned() == null){
	    	shift.setMinAssigned(1);
	    }
	    if(shift.getCreatedDtTm() == null){
        	shift.setCreatedDtTm(new Date());
        }
	    if(this.validMinMax(shift.getMinAssigned(), shift.getMaxAssigned())){
	        this.updateActivityDtTm(shift);
	        return shiftDAO.save(shift);
        }else{
        	return null;
        }
    }
    
    /**
     * Update shift. Fill in missing fields from existing shift object where needed.
     * @param shift    {@link Shift}
     * @return          {@link Shift}
     */
    public Shift update(Shift shift) {
    	this.fillShift(shift);
    	if(this.validMinMax(shift.getMinAssigned(), shift.getMaxAssigned())){
    		return shiftDAO.update(shift);
    	}else{
    		return null;
    	}
    }
    
    /**
     * Delete shift with id
     * @param id        {@link Integer}
     */
    public Boolean delete(Integer id) {
        Shift shift = shiftDAO.get(id);
        if(shift == null){
        	return false;
        }else{
        	shiftDAO.delete(shift);
        	return true;
        }
    }
    
    /**
     * Use existing shift object with id to fill in missing fields for update
     * @param shift    {@link Shift}
     * @return         {@link Shift}
     */
    private Shift fillShift(Shift shift){
        Shift savedShift = shiftDAO.get(shift.getId());
        if(shift.getLabel() == null){
            shift.setLabel(savedShift.getLabel());
        }
        if(shift.getMinAssigned() == null){
            shift.setMinAssigned(savedShift.getMinAssigned());
        }
        if(shift.getMaxAssigned() == null){
            shift.setMaxAssigned(savedShift.getMaxAssigned());
        }
        if(shift.getActiveInd() == null){
            shift.setActiveInd(savedShift.getActiveInd());
        }
        if(shift.getCreatedDtTm() == null){
            shift.setCreatedDtTm(savedShift.getCreatedDtTm());
        }
        this.updateActivityDtTm(shift);
        return shift;
    }
    
    /**
     * Update activityDtTm for shift
     * @param shift    {@link Shift}
     * @return          {@link Shift}
     */
    private Shift updateActivityDtTm(Shift shift){
        if(shift.getActivityDtTm() == null){
            shift.setActivityDtTm(new Date());
        }
        return shift;
    }
    
    /**
     * Validate min/max
     * @param min	Integer
     * @param max	Integer
     * @return		Boolean
     */
    private Boolean validMinMax(Integer min, Integer max){
    	Boolean bool = false;
    	if(min <= max){
    		bool = false;
    	}
    	return bool;
    }
}