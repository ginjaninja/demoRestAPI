package com.ginjaninja.demoRestAPI.shiftAssignment;

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
     * @return          {@link ShiftAssignment}
     */
    public ShiftAssignment save(ShiftAssignment shiftAssignment){
        if(shiftAssignment.getActiveInd() == null){
            shiftAssignment.setActiveInd("Y");
        }
        this.updateActivityDtTm(shiftAssignment);
        return shiftAssignmentDAO.save(shiftAssignment);
    }
    
    /**
     * Update shiftAssignment. Fill in missing fields from existing shiftAssignment object where needed.
     * @param shiftAssignment    {@link ShiftAssignment}
     * @return          {@link ShiftAssignment}
     */
    public ShiftAssignment update(ShiftAssignment shiftAssignment) {
        this.fillShiftAssignment(shiftAssignment);
        return shiftAssignmentDAO.update(shiftAssignment);
    }
    
    /**
     * Delete shiftAssignment with id
     * @param id        {@link Integer}
     */
    public void delete(Integer id) {
        ShiftAssignment shiftAssignment = shiftAssignmentDAO.get(id);
        shiftAssignmentDAO.delete(shiftAssignment);
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
}