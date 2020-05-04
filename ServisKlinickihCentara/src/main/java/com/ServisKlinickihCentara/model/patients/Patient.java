package com.ServisKlinickihCentara.model.patients;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import com.ServisKlinickihCentara.model.clinics.ClinicRating;
import com.ServisKlinickihCentara.model.employees.DoctorRating;
import com.ServisKlinickihCentara.model.employees.Medication;
import com.ServisKlinickihCentara.model.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends User{

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String phoneNumber;

    @Column
    private String insuranceNumber;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
	@JsonBackReference
    private List<Appointment> apointments;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
	@JsonBackReference
    private List<AppointmentRequest> requests;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
	@JsonBackReference
    private List<DoctorRating> doctorRatings;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
	@JsonBackReference
    private List<ClinicRating> clinicRatings;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "patient_medications", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"))
    private List<Medication> medications;
    
    @OneToOne(fetch = FetchType.EAGER)
	@JsonBackReference
    private MedicalRecord medicalRecord;
    

    public Patient(){
        super();
    }
    
    
	public Patient(String address, String city, String country, String phoneNumber, String insuranceNumber,
			List<Appointment> apointments, List<AppointmentRequest> requests, List<DoctorRating> doctorRatings,
			List<ClinicRating> clinicRatings, List<Medication> medications, MedicalRecord medicalRecord) {
		super();
		this.address = address;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.insuranceNumber = insuranceNumber;
		this.apointments = apointments;
		this.requests = requests;
		this.doctorRatings = doctorRatings;
		this.clinicRatings = clinicRatings;
		this.medications = medications;
		this.medicalRecord = medicalRecord;
	}


	public Patient(Long id, String email, String password, String name, String surname, boolean enabled,
			Timestamp lastPasswordResetDate, String address, String city, String country, String phoneNumber, String insuranceNumber) 
	{
		super(id, email, password, name, surname, enabled, lastPasswordResetDate);
		// TODO Auto-generated constructor stub
		this.address = address;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.insuranceNumber = insuranceNumber;
	}


	public Patient(String email, String password, String name, String surname, boolean enabled,
			Timestamp lastPasswordResetDate, String address, String city, String country, String phoneNumber, String insuranceNumber) 
	{
		super(email, password, name, surname, enabled, lastPasswordResetDate);
		// TODO Auto-generated constructor stub
		this.address = address;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.insuranceNumber = insuranceNumber;
	}


	public Patient(String email, String password, String address, String city, String country, String phoneNumber, String insuranceNumber) 
	{
		super(email, password);
		this.address = address;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.insuranceNumber = insuranceNumber;
		// TODO Auto-generated constructor stub
	}


	public Patient(Long id, String email, String password, String name, String surname, boolean enabled,
			Timestamp lastPasswordResetDate, String address, String city, String country, String phoneNumber, String insuranceNumber,
			List<Appointment> apointments, List<AppointmentRequest> requests, List<DoctorRating> doctorRatings,
			List<ClinicRating> clinicRatings, List<Medication> medications, MedicalRecord medicalRecord)
	{
		super(id, email, password, name, surname, enabled, lastPasswordResetDate);
		// TODO Auto-generated constructor stub
		this.address = address;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.insuranceNumber = insuranceNumber;
		this.apointments = apointments;
		this.requests = requests;
		this.doctorRatings = doctorRatings;
		this.clinicRatings = clinicRatings;
		this.medications = medications;
		this.medicalRecord = medicalRecord;
	}


	public Patient(String email, String password, String name, String surname, boolean enabled,
			Timestamp lastPasswordResetDate, String address, String city, String country, String phoneNumber, String insuranceNumber,
			List<Appointment> apointments, List<AppointmentRequest> requests, List<DoctorRating> doctorRatings,
			List<ClinicRating> clinicRatings, List<Medication> medications, MedicalRecord medicalRecord)
	{
		super(email, password, name, surname, enabled, lastPasswordResetDate);
		// TODO Auto-generated constructor stub
		this.address = address;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.insuranceNumber = insuranceNumber;
		this.apointments = apointments;
		this.requests = requests;
		this.doctorRatings = doctorRatings;
		this.clinicRatings = clinicRatings;
		this.medications = medications;
		this.medicalRecord = medicalRecord;
	}


	public Patient(String email, String password) {
		super(email, password);
		// TODO Auto-generated constructor stub
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

	public List<Appointment> getApointments() {
		return apointments;
	}

	public void setApointments(List<Appointment> apointments) {
		this.apointments = apointments;
	}

	public List<AppointmentRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<AppointmentRequest> requests) {
		this.requests = requests;
	}

	public List<DoctorRating> getDoctorRatings() {
		return doctorRatings;
	}

	public void setDoctorRatings(List<DoctorRating> doctorRatings) {
		this.doctorRatings = doctorRatings;
	}

	public List<ClinicRating> getClinicRatings() {
		return clinicRatings;
	}

	public void setClinicRatings(List<ClinicRating> clinicRatings) {
		this.clinicRatings = clinicRatings;
	}


	public List<Medication> getMedications() {
		return medications;
	}

	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
}
