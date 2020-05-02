package com.ServisKlinickihCentara.model.patients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ServisKlinickihCentara.model.clinics.Term;
import com.ServisKlinickihCentara.model.employees.Doctor;
import com.ServisKlinickihCentara.model.enums.AppointmentType;
import com.ServisKlinickihCentara.model.enums.RequestStatus;
import com.ServisKlinickihCentara.model.enums.Specialty;

@Entity
public class AppointmentRequest 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Patient patient;
	
	@Column
	private Term term;
	
	@Column
	private Doctor doctor;
	
	@Column
	private Specialty category;
	
	@Column
	private AppointmentType type;
	
	@Column
	private RequestStatus status;
	
	public AppointmentRequest() {}

	public AppointmentRequest(Patient patient, Term term, Doctor doctor, Specialty category, AppointmentType type) {
		super();
		this.patient = patient;
		this.term = term;
		this.doctor = doctor;
		this.category = category;
		this.type = type;
		this.status = RequestStatus.PENDING;
	}

	public AppointmentRequest(Long id, Patient patient, Term term, Doctor doctor, Specialty category,
			AppointmentType type) {
		super();
		this.id = id;
		this.patient = patient;
		this.term = term;
		this.doctor = doctor;
		this.category = category;
		this.type = type;
		this.status = RequestStatus.PENDING;
	}

	public AppointmentRequest(Patient patient, Term term, Doctor doctor, Specialty category, AppointmentType type,
			RequestStatus status) {
		super();
		this.patient = patient;
		this.term = term;
		this.doctor = doctor;
		this.category = category;
		this.type = type;
		this.status = status;
	}

	public AppointmentRequest(Long id, Patient patient, Term term, Doctor doctor, Specialty category,
			AppointmentType type, RequestStatus status) {
		super();
		this.id = id;
		this.patient = patient;
		this.term = term;
		this.doctor = doctor;
		this.category = category;
		this.type = type;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Specialty getCategory() {
		return category;
	}

	public void setCategory(Specialty category) {
		this.category = category;
	}

	public AppointmentType getType() {
		return type;
	}

	public void setType(AppointmentType type) {
		this.type = type;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}
	
}
