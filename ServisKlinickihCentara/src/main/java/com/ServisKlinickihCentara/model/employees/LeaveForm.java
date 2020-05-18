package com.ServisKlinickihCentara.model.employees;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class LeaveForm 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private LocalDate startDate;
	
	@Column 
	private LocalDate endDate;

	@ManyToOne
	@JsonManagedReference
	private Employee employee;
	
	@Column
	private boolean active;
	
	@OneToOne
	private LeaveFormRequest request;
	
	@Column
	private String description;
	
	public LeaveForm() {}

	public LeaveForm(Long id, LocalDate startDate, LocalDate endDate, Employee employee, boolean active,
			LeaveFormRequest request, String description) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employee = employee;
		this.active = active;
		this.request = request;
		this.description = description;
	}

	public LeaveForm(LocalDate startDate, LocalDate endDate, Employee employee, boolean active, LeaveFormRequest request,
			String description) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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
