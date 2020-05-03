package com.ServisKlinickihCentara.model.employees;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.enums.Specialty;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@DiscriminatorValue("DOCTOR")
public class Doctor extends Employee
{
	@Column
	private Specialty specialty;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "doctor")
	@JsonBackReference
	private List<DoctorRating> ratings;



	public Doctor () { super(); }
	
	

	public Doctor(Long id, String email, String password, String name, String surname, boolean enabled,
			Timestamp lastPasswordResetDate, Clinic clinic, Timestamp shiftStart, Timestamp shiftEnd, Specialty specialty) {
		super(id, email, password, name, surname, enabled, lastPasswordResetDate, clinic, shiftStart, shiftEnd);
		// TODO Auto-generated constructor stub
		this.specialty = specialty;
	}



	public Doctor(String email, String password, String name, String surname, boolean enabled,
			Timestamp lastPasswordResetDate, Clinic clinic, Timestamp shiftStart, Timestamp shiftEnd, Specialty specialty) {
		super(email, password, name, surname, enabled, lastPasswordResetDate, clinic, shiftStart, shiftEnd);
		// TODO Auto-generated constructor stub
		this.specialty = specialty;
	}



	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public List<DoctorRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<DoctorRating> ratings) {
		this.ratings = ratings;
	}
	
	
	
}
