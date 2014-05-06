package com.ginjaninja.demoRestAPI.shift;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.ginjaninja.demoRestAPI.person.Person;

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
	
	@Column(name = "start_dt_tm") 
    private Date startDtTm;
	
	@Column(name = "end_dt_tm") 
    private Date endDtTm;
	
	@Column(name = "active_ind", length = 1, nullable = false)
    private String activeInd;
     
    @Column(name = "activity_dt_tm", nullable = false) 
    private Date activityDtTm;
    
    @Column(name = "created_dt_tm", nullable = false) 
    private Date createdDtTm;
    
    
    public ShiftAssignment(){
    	
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

	public Date getStartDtTm() {
		return startDtTm;
	}

	public void setStartDtTm(Date startDtTm) {
		this.startDtTm = startDtTm;
	}

	public Date getEndDtTm() {
		return endDtTm;
	}

	public void setEndDtTm(Date endDtTm) {
		this.endDtTm = endDtTm;
	}
}
