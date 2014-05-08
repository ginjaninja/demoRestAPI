package com.ginjaninja.demoRestAPI.shiftAssignment;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ShiftAssignmentDTO {
	private static final String DATE_FORMAT = "MM/dd/yyyy";
	
	private Integer id;
	private Integer personId;
	private Integer shiftId;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=DATE_FORMAT, timezone="America/New_York")
	private Date shiftDt;
	
	private String activeInd;
    private Date activityDtTm;
    private Date createdDtTm;
    private Boolean checkConflict;
    
    
    public ShiftAssignmentDTO(){
    	
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getShiftId() {
		return shiftId;
	}

	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
	}

	public Date getShiftDt() {
		return shiftDt;
	}

	public void setShiftDt(Date shiftDt) {
		this.shiftDt = shiftDt;
	}

	public String getActiveInd() {
		return activeInd;
	}

	public void setActiveInd(String activeInd) {
		this.activeInd = activeInd;
	}

	public Date getActivityDtTm() {
		return activityDtTm;
	}

	public void setActivityDtTm(Date activityDtTm) {
		this.activityDtTm = activityDtTm;
	}

	public Date getCreatedDtTm() {
		return createdDtTm;
	}

	public void setCreatedDtTm(Date createdDtTm) {
		this.createdDtTm = createdDtTm;
	}

	public Boolean getCheckConflict() {
		return checkConflict;
	}

	public void setCheckConflict(Boolean checkConflict) {
		this.checkConflict = checkConflict;
	}
    
    
}
