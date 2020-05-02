package com.ServisKlinickihCentara.model.patients;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.ServisKlinickihCentara.model.clinics.ClinicRating;
import com.ServisKlinickihCentara.model.employees.DoctorRating;
import com.ServisKlinickihCentara.model.employees.Medication;
import com.ServisKlinickihCentara.model.users.User;

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
    
    @Column 
    private List<Appointment> apointments; //TODO: Can be empty
    
    @Column
    private List<AppointmentRequest> requests; //TODO: Can be empty
    
    @Column 
    private List<DoctorRating> doctorRatings; //TODO: Can be empty
    
    @Column
    private List<ClinicRating> clinicRatings; //TODO: Can be empty
    
    @Column
    private List<Medication> medsList; //TODO: Can be empty
    
    @Column
    private MedicalRecord medicalRecord;
    

    public Patient(){
        super();
    }
    
    
	public Patient(String address, String city, String country, String phoneNumber, String insuranceNumber,
			List<Appointment> apointments, List<AppointmentRequest> requests, List<DoctorRating> doctorRatings,
			List<ClinicRating> clinicRatings, List<Medication> medsList, MedicalRecord medicalRecord) {
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
		this.medsList = medsList;
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
			List<ClinicRating> clinicRatings, List<Medication> medsList, MedicalRecord medicalRecord) 
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
		this.medsList = medsList;
		this.medicalRecord = medicalRecord;
	}


	public Patient(String email, String password, String name, String surname, boolean enabled,
			Timestamp lastPasswordResetDate, String address, String city, String country, String phoneNumber, String insuranceNumber,
			List<Appointment> apointments, List<AppointmentRequest> requests, List<DoctorRating> doctorRatings,
			List<ClinicRating> clinicRatings, List<Medication> medsList, MedicalRecord medicalRecord) 
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
		this.medsList = medsList;
		this.medicalRecord = medicalRecord;
	}


	public Patient(String email, String password) {
		super(email, password);
		// TODO Auto-generated constructor stub
	}

 
    

}
