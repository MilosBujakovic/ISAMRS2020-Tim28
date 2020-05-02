package com.ServisKlinickihCentara.model.patients;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ServisKlinickihCentara.model.enums.BloodType;
import com.ServisKlinickihCentara.model.enums.RhType;

@Entity
public class MedicalRecord 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Patient patient;
	
	@Column
	private Long age;
	
	@Column
	private double height;
	
	@Column 
	private double weight;
	
	@Column 
	private BloodType bloodtype;
	
	@Column
	private RhType rhfactor;
	
	@Column
	private List<AppointmentReport> reports;
	
	public MedicalRecord() {}

	public MedicalRecord(Long id, Patient patient, Long age, double height, double weight, BloodType bloodtype,
			RhType rhfactor, List<AppointmentReport> reports) {
		super();
		this.id = id;
		this.patient = patient;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.bloodtype = bloodtype;
		this.rhfactor = rhfactor;
		this.reports = reports;
	}

	public MedicalRecord(Patient patient, Long age, double height, double weight, BloodType bloodtype, RhType rhfactor,
			List<AppointmentReport> reports) {
		super();
		this.patient = patient;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.bloodtype = bloodtype;
		this.rhfactor = rhfactor;
		this.reports = reports;
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

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public BloodType getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(BloodType bloodtype) {
		this.bloodtype = bloodtype;
	}

	public RhType getRhfactor() {
		return rhfactor;
	}

	public void setRhfactor(RhType rhfactor) {
		this.rhfactor = rhfactor;
	}

	public List<AppointmentReport> getReports() {
		return reports;
	}

	public void setReports(List<AppointmentReport> reports) {
		this.reports = reports;
	}
	
		
	
}
