package com.ServisKlinickihCentara.model.patients;

import java.util.List;

import javax.persistence.*;

import com.ServisKlinickihCentara.model.enums.BloodType;
import com.ServisKlinickihCentara.model.enums.RhType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class MedicalRecord 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JsonManagedReference
	private Patient patient;
	
	@Column
	private int age;
	
	@Column
	private double height;
	
	@Column 
	private double weight;

	@Column
	private String diopter;

	@Column
	private String alergies;
	
	@Column 
	private BloodType bloodtype;
	
	@Column
	private RhType rhfactor;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "medicalRecord")
	@JsonBackReference
	private List<AppointmentReport> reports;
	
	public MedicalRecord() {}

	public MedicalRecord(Long id, Patient patient, int age, double height, double weight, BloodType bloodtype,
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

	public MedicalRecord(Patient patient, int age, double height, double weight, BloodType bloodtype, RhType rhfactor,
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

	public MedicalRecord(Long id, Patient patient, int age, double height, double weight, String diopter, String alergies, BloodType bloodtype, RhType rhfactor, List<AppointmentReport> reports) {
		this.id = id;
		this.patient = patient;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.diopter = diopter;
		this.alergies = alergies;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
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

	public String getDiopter() {
		return diopter;
	}

	public void setDiopter(String diopter) {
		this.diopter = diopter;
	}

	public String getAlergies() {
		return alergies;
	}

	public void setAlergies(String alergies) {
		this.alergies = alergies;
	}
}
