package com.ServisKlinickihCentara.dto.doctorDTO;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.employees.LeaveForm;
import com.ServisKlinickihCentara.model.employees.LeaveFormRequest;
import com.ServisKlinickihCentara.model.patients.Appointment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class DoctorEditDTO 
{
	private String email;
	private String name;
	private String surname;
	private Long id;
	private String specialty;
	private String password;
	private String lastPasswordchange;
	private String address;
	
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
}
