package com.ServisKlinickihCentara.model.employees;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LeaveForm 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Timestamp startTime;
	
	@Column 
	private Timestamp endTime;
	
	@Column
	private Employee employee;
	
	@Column
	private boolean active;
	
	@Column 
	private LeaveFormRequest request;
	
	@Column
	private String description;
	
	public LeaveForm() {}

	public LeaveForm(Long id, Timestamp startTime, Timestamp endTime, Employee employee, boolean active,
			LeaveFormRequest request, String description) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.employee = employee;
		this.active = active;
		this.request = request;
		this.description = description;
	}

	public LeaveForm(Timestamp startTime, Timestamp endTime, Employee employee, boolean active, LeaveFormRequest request,
			String description) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.employee = employee;
		this.active = active;
		this.request = request;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LeaveFormRequest getRequest() {
		return request;
	}

	public void setRequest(LeaveFormRequest request) {
		this.request = request;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
