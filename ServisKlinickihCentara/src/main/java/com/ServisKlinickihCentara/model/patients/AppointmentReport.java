package com.ServisKlinickihCentara.model.patients;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ServisKlinickihCentara.model.clinics.Diagnosis;
import com.ServisKlinickihCentara.model.employees.Prescription;

@Entity
public class AppointmentReport 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Diagnosis diagnosis;
	
	@Column
	private String description;
	
	@Column
	private List<Prescription> prescriptions;
	
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
	
}
