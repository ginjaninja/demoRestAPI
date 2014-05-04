package com.ginjaninja.demoRestAPI.person;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
@Table(name = "PERSON")
public class Person implements Serializable {
 
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Column(name = "active_ind", length = 1, nullable = false)
    private String activeInd;
     
    @Column(name = "activity_dt_tm", nullable = false) 
    private Date activityDtTm;
    
    public Person() {}
 
    public Person(String firstName, String lastName, String activeInd, Date activityDtTm) {
    	this.setFirstName(firstName);
    	this.setLastName(lastName);
    	this.setActiveInd(activeInd);
    	this.setActivityDtTm(activityDtTm);
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
    
	@Override
	public String toString(){
		return ReflectionToStringBuilder.toString(this);
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
    
}
