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
	
	
}