package com.ginjaninja.demoRestAPI.shift;

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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@NamedQueries(value=
    @NamedQuery(
        name="getAllShifts",
        query="SELECT s FROM Shift s"
    )
)

@Entity
public class Shift implements Serializable {
	@Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "label", length = 50, nullable = false)
	private String label;
	
	@Column(name = "min_assigned", length = 3, nullable = false)
	private Integer minAssigned;
	
	@Column(name = "max_assigned", length = 3, nullable = false)
	private Integer maxAssigned;
	
	@Column(name = "active_ind", length = 1, nullable = false)
    private String activeInd;
     
    @Column(name = "activity_dt_tm", nullable = false) 
    private Date activityDtTm;
    
    @Column(name = "created_dt_tm", nullable = false) 
    private Date createdDtTm;
	
    @OneToMany(mappedBy = "shift")
    private Set<ShiftAssignment> shiftAssignments = new HashSet<ShiftAssignment>();
    
    
    public Shift(){
    	
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getMinAssigned() {
		return minAssigned;
	}

	public void setMinAssigned(Integer minAssigned) {
		this.minAssigned = minAssigned;
	}

	public Integer getMaxAssigned() {
		return maxAssigned;
	}

	public void setMaxAssigned(Integer maxAssigned) {
		this.maxAssigned = maxAssigned;
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
