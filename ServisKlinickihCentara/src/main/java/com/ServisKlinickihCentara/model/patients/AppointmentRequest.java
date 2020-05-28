package com.ServisKlinickihCentara.model.patients;

import javax.persistence.*;

import com.ServisKlinickihCentara.model.clinics.Term;
import com.ServisKlinickihCentara.model.clinics.TypeOfExam;
import com.ServisKlinickihCentara.model.employees.Doctor;
import com.ServisKlinickihCentara.model.enums.AppointmentType;
import com.ServisKlinickihCentara.model.enums.RequestStatus;
import com.ServisKlinickihCentara.model.enums.Specialty;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class AppointmentRequest 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JsonManagedReference
	private Patient patient;
	
	@ManyToOne
	private Term term;
	
	@ManyToOne
	private Doctor doctor;
	
	@ManyToOne
	private TypeOfExam typeOfExam;
	
	@Column
	private AppointmentType type;
	
	@Column
	private RequestStatus status;
	
	public AppointmentRequest() {}

	public AppointmentRequest(Patient patient, Term term, Doctor doctor, TypeOfExam typeOfExam, AppointmentType type) {
		super();
		this.patient = patient;
		this.term = term;
		this.doctor = doctor;
		this.typeOfExam = typeOfExam;
		this.type = type;
		this.status = RequestStatus.PENDING;
	}

	public AppointmentRequest(Long id, Patient patient, Term term, Doctor doctor, TypeOfExam typeOfExam,
			AppointmentType type) {
		super();
		this.id = id;
		this.patient = patient;
		this.term = term;
		this.doctor = doctor;
		this.typeOfExam = typeOfExam;
		this.type = type;
		this.status = RequestStatus.PENDING;
	}

	public AppointmentRequest(Patient patient, Term term, Doctor doctor, TypeOfExam typeOfExam, AppointmentType type,
			RequestStatus status) {
		super();
		this.patient = patient;
		this.term = term;
		this.doctor = doctor;
		this.typeOfExam = typeOfExam;
		this.type = type;
		this.status = status;
	}

	public AppointmentRequest(Long id, Patient patient, Term term, Doctor doctor, TypeOfExam typeOfExam,
			AppointmentType type, RequestStatus status) {
		super();
		this.id = id;
		this.patient = patient;
		this.term = term;
		this.doctor = doctor;
		this.typeOfExam = typeOfExam;
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

	public TypeOfExam getTypeOfExam() {
		return typeOfExam;
	}

	public void setTypeOfExam(TypeOfExam typeOfExam) {
		this.typeOfExam = typeOfExam;
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
