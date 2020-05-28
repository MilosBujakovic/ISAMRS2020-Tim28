package com.ServisKlinickihCentara.model.clinics;

import javax.persistence.*;

import com.ServisKlinickihCentara.model.enums.AppointmentType;
import com.ServisKlinickihCentara.model.enums.PatientType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	@OneToOne
	@JsonManagedReference
	private TypeOfExam typeOfExam;

	@Column
	private Double discount;
	
	public PriceItem() {}


	public PriceItem(AppointmentType type, double price, double discount) {
		super();
		this.type = type;
		this.basePrice = price;
		this.discount = discount;
	}

	public PriceItem(Long id, AppointmentType type, double basePrice, TypeOfExam typeOfExam, double discount) {
		this.id = id;
		this.type = type;
		this.basePrice = basePrice;
		this.typeOfExam = typeOfExam;
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

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public TypeOfExam getTypeOfExam() {
		return typeOfExam;
	}

	public void setTypeOfExam(TypeOfExam typeOfExam) {
		this.typeOfExam = typeOfExam;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}


	
	
}
