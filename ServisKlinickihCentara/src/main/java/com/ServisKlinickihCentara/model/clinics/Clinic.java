package com.ServisKlinickihCentara.model.clinics;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ServisKlinickihCentara.model.employees.Doctor;
import com.ServisKlinickihCentara.model.employees.Nurse;
import com.ServisKlinickihCentara.model.users.ClinicAdmin;

@Entity
public class Clinic 
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String address;
	
	@Column
	private List<Doctor> staff;
	
	@Column 
	private List<Nurse> assistingStaff;
	
	@Column 
	private List<Room> rooms;
	
	@Column
	private List<PriceItem> pricelist;
	
	@Column
	private List<Term> freeTerms;
	
	@Column 
	private List<ClinicAdmin> admins;
	
	
	public Clinic() {};
	
	public Clinic(String name, String address)
	{
		this.name = name;
		this.address = address;
	}

	public Clinic(String name, String address, List<Doctor> staff, List<Nurse> assistingStaff, List<Room> rooms,
			List<PriceItem> pricelist, List<Term> freeTerms, List<ClinicAdmin> admins) {
		super();
		this.name = name;
		this.address = address;
		this.staff = staff;
		this.assistingStaff = assistingStaff;
		this.rooms = rooms;
		this.pricelist = pricelist;
		this.freeTerms = freeTerms;
		this.admins = admins;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Doctor> getStaff() {
		return staff;
	}

	public void setStaff(List<Doctor> staff) {
		this.staff = staff;
	}

	public List<Nurse> getAssistingStaff() {
		return assistingStaff;
	}

	public void setAssistingStaff(List<Nurse> assistingStaff) {
		this.assistingStaff = assistingStaff;
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

	public List<Term> getFreeTerms() {
		return freeTerms;
	}

	public void setFreeTerms(List<Term> freeTerms) {
		this.freeTerms = freeTerms;
	}

	public List<ClinicAdmin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<ClinicAdmin> admins) {
		this.admins = admins;
	}
	
	
	
	
}
