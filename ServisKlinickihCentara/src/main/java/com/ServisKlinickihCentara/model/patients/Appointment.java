package com.ServisKlinickihCentara.model.patients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ServisKlinickihCentara.model.clinics.Term;
import com.ServisKlinickihCentara.model.employees.Doctor;
import com.ServisKlinickihCentara.model.enums.AppointmentType;
import com.ServisKlinickihCentara.model.enums.Specialty;

@Entity
public class Appointment 
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
	private AppointmentReport report;
	
	@Column
	private boolean predefined;
	
	@Column
	private boolean active;
	
	@Column
	private boolean cancelled;

	public Appointment(Long id, Patient patient, Term term, Doctor doctor, Specialty category, AppointmentType type,
			AppointmentReport report, boolean predefined, boolean active, boolean cancelled) {
		super();
		this.id = id;
		this.patient = patient;
		this.term = term;
		this.doctor = doctor;
		this.category = category;
		this.type = type;
		this.report = report;
		this.predefined = predefined;
		this.active = active;
		this.cancelled = cancelled;
	}

	public Appointment(Patient patient, Term term, Doctor doctor, Specialty category, AppointmentType type,
			AppointmentReport report, boolean predefined, boolean active, boolean cancelled) {
		super();
		this.patient = patient;
		this.term = term;
		this.doctor = doctor;
		this.category = category;
		this.type = type;
		this.report = report;
		this.predefined = predefined;
		this.active = active;
		this.cancelled = cancelled;
	}

	public Appointment(Patient patient, Term term, Doctor doctor, Specialty category, AppointmentType type,
			AppointmentReport report, boolean predefined) {
		super();
		this.patient = patient;
		this.term = term;
		this.doctor = doctor;
		this.category = category;
		this.type = type;
		this.report = report;
		this.predefined = predefined;
		this.active = true;
		this.cancelled = false;
	}

	public Appointment(Patient patient, Term term, Doctor doctor, Specialty category, AppointmentType type,
			AppointmentReport report) {
		super();
		this.patient = patient;
		this.term = term;
		this.doctor = doctor;
		this.category = category;
		this.type = type;
		this.report = report;
		this.predefined = false;
		this.active = true;
		this.cancelled = false;
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

	public AppointmentReport getReport() {
		return report;
	}

	public void setReport(AppointmentReport report) {
		this.report = report;
	}

	public boolean isPredefined() {
		return predefined;
	}

	public void setPredefined(boolean predefined) {
		this.predefined = predefined;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	
	
	
}
