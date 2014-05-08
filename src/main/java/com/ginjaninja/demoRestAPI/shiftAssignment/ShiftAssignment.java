package com.ginjaninja.demoRestAPI.shiftAssignment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ginjaninja.demoRestAPI.person.Person;
import com.ginjaninja.demoRestAPI.shift.Shift;

@NamedQueries({
	@NamedQuery(
	    name="findAllShiftAssignmentsForDateRange",
	    query="SELECT a FROM ShiftAssignment a WHERE :start <= a.shiftDt AND :end >= a.shiftDt AND a.activeInd = 'Y' "
	),
	@NamedQuery(
	    name="findAllShiftsForDateRange",
	    query="SELECT a FROM ShiftAssignment a "
	    		+ "INNER join a.shift s "
	    		+ "INNER join a.person p "
	    		+ "WHERE :start <= a.shiftDt AND :end >= a.shiftDt "
	    		+ "AND a.activeInd = 'Y' "
	    		+ "AND s.id = :shiftId"
	),
	@NamedQuery(
		    name="findAllPersonShiftsForDateRange",
		    query="SELECT a FROM ShiftAssignment a "
		    		+ "INNER join a.person p "
		    		+ "WHERE :start <= a.shiftDt AND :end >= a.shiftDt "
		    		+ "AND a.activeInd = 'Y' "
		    		+ "AND p.id = :personId"
		)
})

@Entity
@Table(name = "shift_assignment")
public class ShiftAssignment implements Serializable {
	@Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "shift_id")
	private Shift shift;
	
	@Column(name = "shift_date", nullable = false) 
    private Date shiftDt;
	
	@Column(name = "active_ind", length = 1, nullable = false)
    private String activeInd;
     
    @Column(name = "activity_dt_tm", nullable = false) 
    private Date activityDtTm;
    
    @Column(name = "created_dt_tm", nullable = false) 
    private Date createdDtTm;
    
    
    public ShiftAssignment(){
    	
    }
 
    public void convertFromDTO(ShiftAssignmentDTO dto){
    	if(dto.getId() != null){
    		this.setId(dto.getId());
    	}
    	Person p = new Person();
    	p.setId(dto.getPersonId());
    	this.setPerson(p);
    	
    	Shift s = new Shift();
    	s.setId(dto.getShiftId());
    	this.setShift(s);
    	
    	this.setShiftDt(dto.getShiftDt());
    	this.setActiveInd(dto.getActiveInd());
    	this.setActivityDtTm(dto.getActivityDtTm());
    	this.setCreatedDtTm(dto.getCreatedDtTm());
    }
    
	@Override
	public String toString(){
		return ReflectionToStringBuilder.toString(this);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
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

	public Date getShiftDt() {
		return shiftDt;
	}

	public void setShiftDt(Date shiftDt) {
		this.shiftDt = shiftDt;
	}

}
