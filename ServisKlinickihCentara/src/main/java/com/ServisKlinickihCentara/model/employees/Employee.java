package com.ServisKlinickihCentara.model.employees;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.patients.Appointment;
import com.ServisKlinickihCentara.model.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User
{
	@Column
	private boolean onVacation;
	
	@Column
	private Timestamp returnDate;

	@ManyToOne
	@JsonManagedReference
	private Clinic clinic;
	
	@Column
	private Time shiftStart;
	
	@Column
	private Time shiftEnd;


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	@JsonBackReference
	private List<LeaveForm> vacations;

	@OneToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	private LeaveFormRequest pendingRequest;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	@JsonBackReference
	private List<Appointment> workingCalendar;
	
	public Employee() { super(); }
	
	

	public Employee(Long id, String email, String password, String name, String surname, boolean enabled,
			Timestamp lastPasswordResetDate, Clinic clinic, Time shiftStart, Time shiftEnd) {
		super(id, email, password, name, surname, enabled, lastPasswordResetDate);
		// TODO Auto-generated constructor stub
		this.onVacation = false;
		this.clinic = clinic;
		this.shiftStart = shiftStart;
		this.shiftEnd = shiftEnd;
		
	}



	public Employee(String email, String password, String name, String surname, boolean enabled,
			Timestamp lastPasswordResetDate, Clinic clinic, Time shiftStart, Time shiftEnd) {
		super(email, password, name, surname, enabled, lastPasswordResetDate);
		// TODO Auto-generated constructor stub
		this.onVacation = false;
		this.clinic = clinic;
		this.shiftStart = shiftStart;
		this.shiftEnd = shiftEnd;
	}


	public boolean isOnVacation() {
		return onVacation;
	}

	public void setOnVacation(boolean onVacation) {
		this.onVacation = onVacation;
	}

	public Timestamp getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Timestamp returnDate) {
		this.returnDate = returnDate;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Time getShiftStart() {
		return shiftStart;
	}

	public void setShiftStart(Time shiftStart) {
		this.shiftStart = shiftStart;
	}

	public Time getShiftEnd() {
		return shiftEnd;
	}

	public void setShiftEnd(Time shiftEnd) {
		this.shiftEnd = shiftEnd;
	}

	public List<LeaveForm> getVacations() {
		return vacations;
	}

	public void setVacations(List<LeaveForm> vacations) {
		this.vacations = vacations;
	}

	public LeaveFormRequest getPendingRequest() {
		return pendingRequest;
	}

	public void setPendingRequest(LeaveFormRequest pendingRequest) {
		this.pendingRequest = pendingRequest;
	}

	public List<Appointment> getWorkingCalendar() {
		return workingCalendar;
	}

	public void setWorkingCalendar(List<Appointment> workingCalendar) {
		this.workingCalendar = workingCalendar;
	}
	
	
	
}
