package com.ServisKlinickihCentara.model.employees;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ServisKlinickihCentara.model.patients.Patient;

@Entity
public class DoctorRating 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Doctor doctor;
	
	@Column 
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
	
	
}
