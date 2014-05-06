package com.ginjaninja.demoRestAPI.person;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ginjaninja.demoRestAPI.shift.ShiftAssignment;

@NamedQueries(value=
	@NamedQuery(
	    name="getAllPeople",
	    query="SELECT p FROM Person p"
	)
)

@Entity
public class Person implements Serializable {
 
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    
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
    
    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private Set<ShiftAssignment> shiftAssignments = new HashSet<ShiftAssignment>();
    
    
    public Person() {}

     
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


	public Set<ShiftAssignment> getShiftAssignments() {
		return shiftAssignments;
	}


	public void setShiftAssignments(Set<ShiftAssignment> shiftAssignments) {
		this.shiftAssignments = shiftAssignments;
	}
	
	
}
