package com.ginjaninja.demoRestAPI.shift;

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
        this.updateActivityDtTm(shift);
        return shiftDAO.save(shift);
    }
    
    /**
     * Update shift. Fill in missing fields from existing shift object where needed.
     * @param shift    {@link Shift}
     * @return          {@link Shift}
     */
    public Shift update(Shift shift) {
        this.fillShift(shift);
        return shiftDAO.update(shift);
    }
    
    /**
     * Delete shift with id
     * @param id        {@link Integer}
     */
    public void delete(Integer id) {
        Shift shift = shiftDAO.get(id);
        shiftDAO.delete(shift);
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
}