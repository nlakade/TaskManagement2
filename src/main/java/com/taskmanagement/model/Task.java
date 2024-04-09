package com.taskmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Task")

public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	
	private String name;
	
	private String description;
	
	private String department;
	
	private int assignerId;
	
	private int assigneeId;
	
	private String assignerStatus;
	
	private String assignerRemark;
	
	private String assigneeStatus;
	
	private String assigneeRemark;
	
	private String startDate;
	
	private String endDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getAssignerId() {
		return assignerId;
	}

	public void setAssignerId(int assignerId) {
		this.assignerId = assignerId;
	}

	public int getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(int assigneeId) {
		this.assigneeId = assigneeId;
	}

	public String getAssignerStatus() {
		return assignerStatus;
	}

	public void setAssignerStatus(String assignerStatus) {
		this.assignerStatus = assignerStatus;
	}

	public String getAssignerRemark() {
		return assignerRemark;
	}

	public void setAssignerRemark(String assignerRemark) {
		this.assignerRemark = assignerRemark;
	}

	public String getAssigneeStatus() {
		return assigneeStatus;
	}

	public void setAssigneeStatus(String assigneeStatus) {
		this.assigneeStatus = assigneeStatus;
	}

	public String getAssigneeRemark() {
		return assigneeRemark;
	}

	public void setAssigneeRemark(String assigneeRemark) {
		this.assigneeRemark = assigneeRemark;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
