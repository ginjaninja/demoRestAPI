package com.ginjaninja.demoRestAPI.person;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.ginjaninja.restAPI.shifts.ShiftAssignment;

@Entity
@Table(name = "PERSON")
public class Person implements Serializable {
 
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    
    @Column(name = "first_name", length = 30)
    private String firstName;
    
    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;
    
    @Column(name = "active_ind", length = 1, nullable = false)
    private String activeInd;
     
    @Column(name = "activity_dt_tm", nullable = false) 
    private Date activityDtTm;
    
    @Column(name = "created_dt_tm", nullable = false) 
    private Date createdDtTm;
    
    @OneToMany
    private Set<ShiftAssignment> shifts = new HashSet<ShiftAssignment>();
    
    
    public Person() {}

     
    @Override
	public String toString(){
		return ReflectionToStringBuilder.toString(this);
	}
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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


	public Set<ShiftAssignment> getShifts() {
		return shifts;
	}


	public void setShifts(Set<ShiftAssignment> shifts) {
		this.shifts = shifts;
	}

}
