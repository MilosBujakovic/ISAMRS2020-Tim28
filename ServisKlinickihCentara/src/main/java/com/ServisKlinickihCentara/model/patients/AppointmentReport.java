package com.ServisKlinickihCentara.model.patients;

import java.awt.*;
import java.util.List;

import javax.persistence.*;

import com.ServisKlinickihCentara.model.clinics.Diagnosis;
import com.ServisKlinickihCentara.model.employees.Prescription;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class AppointmentReport 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Diagnosis diagnosis;
	
	@Column
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Prescription> prescriptions;


	@ManyToOne
	@JsonManagedReference
	private MedicalRecord medicalRecord;

	public AppointmentReport() {}

	public AppointmentReport(Long id, Diagnosis diagnosis, String report, List<Prescription> prescriptions) {
		super();
		this.id = id;
		this.diagnosis = diagnosis;
		this.description = report;
		this.prescriptions = prescriptions;
	}

	public AppointmentReport(Diagnosis diagnosis, String report, List<Prescription> prescriptions) {
		super();
		this.diagnosis = diagnosis;
		this.description = report;
		this.prescriptions = prescriptions;
	}


	public AppointmentReport(Long id, Diagnosis diagnosis, String description, List<Prescription> prescriptions, MedicalRecord medicalRecord) {
		this.id = id;
		this.diagnosis = diagnosis;
		this.description = description;
		this.prescriptions = prescriptions;
		this.medicalRecord = medicalRecord;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getReport() {
		return description;
	}

	public void setReport(String report) {
		this.description = report;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
}
