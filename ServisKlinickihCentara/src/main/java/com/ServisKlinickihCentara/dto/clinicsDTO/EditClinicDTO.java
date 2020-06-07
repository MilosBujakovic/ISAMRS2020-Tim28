package com.ServisKlinickihCentara.dto.clinicsDTO;

import java.util.ArrayList;
import java.util.List;

import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.clinics.PriceItem;
import com.ServisKlinickihCentara.model.clinics.Room;
import com.ServisKlinickihCentara.model.employees.Doctor;
import com.ServisKlinickihCentara.model.employees.Nurse;

public class EditClinicDTO 
{
	private String name;//TODO: Unique
	private String address;
	private String description;
	private String admin;
	
	//TODO: As "Advanced Options"
	private List<Doctor> doctors;
	private List<Nurse> nurses;
	private List<Room> rooms;
	private List<PriceItem> pricelist;
	
	//EDitfdslgfgfglkgl->Clinic
	
	
	public EditClinicDTO() { }
	
	public EditClinicDTO(String name, String address, String description)
	{
		this.name = name;
		this.address = address;
		this.description = description;
		doctors = new ArrayList<Doctor>();
		nurses = new ArrayList<Nurse>();
		rooms = new ArrayList<Room>();
		pricelist = new ArrayList<PriceItem>();
	}
	
	public EditClinicDTO(String name, String address, String description, String admin)
	{
		this.name = name;
		this.address = address;
		this.description = description;
		this.admin = admin;
		nurses = new ArrayList<Nurse>();
		doctors = new ArrayList<Doctor>();
		rooms = new ArrayList<Room>();
		pricelist = new ArrayList<PriceItem>();
	}
	
	
	public EditClinicDTO(Clinic clinic)
	{
		this.name = clinic.getName();
		this.address = clinic.getAddress();
		this.description = clinic.getDescription();
		this.admin = clinic.getAdmin().getEmail();
		this.nurses = clinic.getAssistingStaff();
		this.doctors = clinic.getStaff();
		this.rooms = clinic.getRooms();
		this.pricelist = clinic.getPricelist();
	}

	public EditClinicDTO(String name, String address, String description, String admin, List<Doctor> doctors,
			List<Nurse> nurses, List<Room> rooms, List<PriceItem> pricelist) 
	{
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.admin = admin;
		this.doctors = doctors;
		this.nurses = nurses;
		this.rooms = rooms;
		this.pricelist = pricelist;
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

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public List<Nurse> getNurses() {
		return nurses;
	}

	public void setNurses(List<Nurse> nurses) {
		this.nurses = nurses;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public List<PriceItem> getPricelist() {
		return pricelist;
	}

	public void setPricelist(List<PriceItem> pricelist) {
		this.pricelist = pricelist;
	}
	
	
}
