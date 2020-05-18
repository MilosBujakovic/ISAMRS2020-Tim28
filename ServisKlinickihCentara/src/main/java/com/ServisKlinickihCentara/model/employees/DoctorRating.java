package com.ServisKlinickihCentara.model.employees;

import javax.persistence.*;

import com.ServisKlinickihCentara.model.patients.Patient;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class DoctorRating 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonManagedReference
	private Doctor doctor;

	@ManyToOne
	@JsonManagedReference
	private Patient patient;
	
	@Column
	private double grade;

	public DoctorRating() {} ;
	
	public DoctorRating(Long id, Doctor doctor, Patient patient, double grade) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.patient = patient;
		this.grade = grade;
	}

	public DoctorRating(Doctor doctor, Patient patient, double grade) {
		super();
		this.doctor = doctor;
		this.patient = patient;
		this.grade = grade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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
