package com.ServisKlinickihCentara.model.clinics;

import javax.persistence.*;

import com.ServisKlinickihCentara.model.patients.Patient;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ClinicRating 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JsonManagedReference
	private Clinic clinic;

	@ManyToOne
	@JsonManagedReference
	private Patient patient;
	
	@Column
	private double grade;

	public ClinicRating() {} ;
	
	public ClinicRating(Long id, Clinic Clinic, Patient patient, double grade) {
		super();
		this.id = id;
		this.clinic = Clinic;
		this.patient = patient;
		this.grade = grade;
	}

	public ClinicRating(Clinic Clinic, Patient patient, double grade) {
		super();
		this.clinic = Clinic;
		this.patient = patient;
		this.grade = grade;
	}

	public ClinicRating(Clinic clinic, Patient patient) {
		this.clinic = clinic;
		this.patient = patient;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
}