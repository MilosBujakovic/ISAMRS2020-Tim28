package com.ServisKlinickihCentara.model.employees;

import javax.persistence.*;

@Entity
public class Prescription 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Medication medication;
	
	@Column
	private int amountPerDay;
	
	@Column
	private boolean verified;
	
	public Prescription() {}

	public Prescription(Long id, Medication medication, int amountPerDay, boolean verified) {
		super();
		this.id = id;
		this.medication = medication;
		this.amountPerDay = amountPerDay;
		this.verified = verified;
	}

	public Prescription(Medication medication, int amountPerDay, boolean verified) {
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

	public int getAmountPerDay() {
		return amountPerDay;
	}

	public void setAmountPerDay(int amountPerDay) {
		this.amountPerDay = amountPerDay;
	}

	public boolean getVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	

	
}
