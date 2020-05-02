package com.ServisKlinickihCentara.model.employees;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Prescription 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Medication medication;
	
	@Column
	private Long amountPerDay;
	
	@Column
	private boolean verified;
	
	public Prescription() {}

	public Prescription(Long id, Medication medication, Long amountPerDay, boolean verified) {
		super();
		this.id = id;
		this.medication = medication;
		this.amountPerDay = amountPerDay;
		this.verified = verified;
	}

	public Prescription(Medication medication, Long amountPerDay, boolean verified) {
		super();
		this.medication = medication;
		this.amountPerDay = amountPerDay;
		this.verified = verified;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Medication getMedication() {
		return medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	public Long getAmountPerDay() {
		return amountPerDay;
	}

	public void setAmountPerDay(Long amountPerDay) {
		this.amountPerDay = amountPerDay;
	}

	public boolean getVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	

	
}
