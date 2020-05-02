package com.ServisKlinickihCentara.model.clinics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ServisKlinickihCentara.model.enums.AppointmentType;
import com.ServisKlinickihCentara.model.enums.PatientType;

@Entity
public class PriceItem 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private AppointmentType type;
	
	@Column
	private double basePrice;
	
	@Column
	private PatientType patient;
	
	@Column 
	private double discount;//TODO: discount for special appointments
	
	public PriceItem() {}
	
	

	public PriceItem(AppointmentType type, double price, PatientType patient, double discount) {
		super();
		this.type = type;
		this.basePrice = price;
		this.patient = patient;
		this.discount = discount;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppointmentType getType() {
		return type;
	}

	public void setType(AppointmentType type) {
		this.type = type;
	}

	public double getPrice() {
		return basePrice;
	}

	public void setPrice(double price) {
		this.basePrice = price;
	}

	public PatientType getPatient() {
		return patient;
	}

	public void setPatient(PatientType patient) {
		this.patient = patient;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	
}
