package com.ServisKlinickihCentara.model.employees;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Medication 
{

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private String manufacturer;
	
	public Medication() {}

	public Medication(String name, String description, String manufacturer) {
		super();
		this.name = name;
		this.description = description;
		this.manufacturer = manufacturer;
	}

	public Medication(Long id, String name, String description, String manufacturer) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.manufacturer = manufacturer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	
}
