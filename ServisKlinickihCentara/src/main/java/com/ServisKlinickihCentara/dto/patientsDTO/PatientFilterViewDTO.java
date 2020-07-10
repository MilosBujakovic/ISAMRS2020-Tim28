package com.ServisKlinickihCentara.dto.patientsDTO;

import com.ServisKlinickihCentara.model.patients.Patient;

public class PatientFilterViewDTO
{
	
		private long id;
		private String name;
		private String surname;
	    private String address;
	    private String city;
	    private String country;
	    private String phoneNumber;
	    private String insuranceNumber;
	    private long clinicId;
	    
	    public PatientFilterViewDTO(){}
	    
	    

		public PatientFilterViewDTO(long id, String name, String surname, String address, String city, String country, String phoneNumber,
				String insuranceNumber, long clinicId) {
			super();
			this.id = id;
			this.name = name;
			this.surname = surname;
			this.address = address;
			this.city = city;
			this.country = country;
			this.phoneNumber = phoneNumber;
			this.insuranceNumber = insuranceNumber;
			this.clinicId = clinicId;
		}
		
		public PatientFilterViewDTO(Patient patient, long clinicId) {
			super();
			this.id = patient.getId();
			this.address = patient.getAddress();
			this.city = patient.getCity();
			this.country = patient.getCountry();
			this.phoneNumber = patient.getPhoneNumber();
			this.insuranceNumber = patient.getInsuranceNumber();
			this.name = patient.getName();
			this.surname = patient.getSurname();
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



		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
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
