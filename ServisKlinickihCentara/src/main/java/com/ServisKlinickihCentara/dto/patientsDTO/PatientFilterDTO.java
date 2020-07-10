package com.ServisKlinickihCentara.dto.patientsDTO;

public class PatientFilterDTO 
{
	private String name;
	private String surname;
    private String city;
    private String insuranceNumber;
	private long clinicId;
    
    public PatientFilterDTO() {}
    
    

	public PatientFilterDTO(String name, String surname, String city, String insuranceNumber, long clinicId) {
		super();
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.insuranceNumber = insuranceNumber;
		this.clinicId = clinicId;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public long getClinicId() {
		return clinicId;
	}

	public void setClinicId(long clinicId) {
		this.clinicId = clinicId;
	}
    
    
}
