package com.ServisKlinickihCentara.dto.clinicsDTO;

import com.ServisKlinickihCentara.model.clinics.Clinic;

public class EditClinicDTO 
{
	private Long id;
	private String name;//TODO: Unique
	private String address;
	private String description;
	private String admin;
	
	//TODO: As "Advanced Options"EDitfdslgfgfglkgl->Clinic
	/*
	private List<Doctor> doctors;
	private List<Nurse> nurses;
	private List<Room> rooms;
	private List<PriceItem> pricelist;
	*/
	
	
	public EditClinicDTO() { }
	
	public EditClinicDTO(Long id, String name, String address, String description)
	{
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
	}
	
	
	
	public EditClinicDTO(Clinic clinic)
	{
		this.id = clinic.getId();
		this.name = clinic.getName();
		this.address = clinic.getAddress();
		this.description = clinic.getDescription();
		this.admin = clinic.getAdmin().getEmail();
	}

	public EditClinicDTO(Long id, String name, String address, String description, String admin) 
	{
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.admin = admin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
