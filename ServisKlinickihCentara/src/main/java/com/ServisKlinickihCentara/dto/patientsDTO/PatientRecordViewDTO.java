package com.ServisKlinickihCentara.dto.patientsDTO;

import java.util.List;

import com.ServisKlinickihCentara.dto.medicalRecordsDTO.DiseaseHistoryDTO;
import com.ServisKlinickihCentara.model.patients.Patient;

public class PatientRecordViewDTO 
{
	private int age;
	private String insuranceNumber;
	private String name;
	private String surname;
	private String address;
	private String city;
	private String phoneNumber;
	private double height;
	private double weight;
	private String diopter;
	private String alergies;
	private String bloodtype;
	private String rhfactor;
	private List<DiseaseHistoryDTO> diseaseHistory;
	
	public PatientRecordViewDTO() {}

	public PatientRecordViewDTO(int age, String insuranceNumber, String name, String surname,
			String address, String city, String phoneNumber, double height, double weight, String diopter,
			String alergies, String bloodtype, String rhfactor, List<DiseaseHistoryDTO> diseaseHistory) {
		super();
		this.age = age;
		this.insuranceNumber = insuranceNumber;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.height = height;
		this.weight = weight;
		this.diopter = diopter;
		this.alergies = alergies;
		this.bloodtype = bloodtype;
		this.rhfactor = rhfactor;
		this.diseaseHistory = diseaseHistory;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getDiopter() {
		return diopter;
	}

	public void setDiopter(String diopter) {
		this.diopter = diopter;
	}

	public String getAlergies() {
		return alergies;
	}

	public void setAlergies(String alergies) {
		this.alergies = alergies;
	}

	public String getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	public String getRhfactor() {
		return rhfactor;
	}

	public void setRhfactor(String rhfactor) {
		this.rhfactor = rhfactor;
	}

	public List<DiseaseHistoryDTO> getDiseaseHistory() {
		return diseaseHistory;
	}

	public void setDiseaseHistory(List<DiseaseHistoryDTO> diseaseHistory) {
		this.diseaseHistory = diseaseHistory;
	}
	
	
}
