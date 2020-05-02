package com.ServisKlinickihCentara.model.clinics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ServisKlinickihCentara.model.patients.Patient;

@Entity
public class ClinicRating 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Clinic clinic;
	
	@Column 
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
	
	
}