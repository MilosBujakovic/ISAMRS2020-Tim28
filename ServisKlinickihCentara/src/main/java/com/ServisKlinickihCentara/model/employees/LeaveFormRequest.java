package com.ServisKlinickihCentara.model.employees;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ServisKlinickihCentara.model.enums.RequestStatus;

@Entity
public class LeaveFormRequest 
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
	private RequestStatus status;
	
	public LeaveFormRequest() {}

	public LeaveFormRequest(Timestamp startTime, Timestamp endTime, Employee employee) 
	{
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.employee = employee;
		this.status = RequestStatus.PENDING;
	}




	public LeaveFormRequest(Long id, Timestamp startTime, Timestamp endTime, Employee employee) 
	{
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.employee = employee;
		this.status = RequestStatus.PENDING;
	}




	public LeaveFormRequest(Long id, Timestamp startTime, Timestamp endTime, Employee employee, RequestStatus status) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.employee = employee;
		this.status = status;
	}




	public LeaveFormRequest(Timestamp startTime, Timestamp endTime, Employee employee, RequestStatus status) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.employee = employee;
		this.status = status;
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

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}
	
	
	
}
