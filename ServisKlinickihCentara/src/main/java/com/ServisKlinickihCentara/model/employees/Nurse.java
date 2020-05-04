package com.ServisKlinickihCentara.model.employees;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.ServisKlinickihCentara.model.clinics.Clinic;

@Entity
@DiscriminatorValue("NURSE")
public class Nurse extends Employee
{

	public Nurse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Nurse(Long id, String email, String password, String name, String surname, boolean enabled,
				 Timestamp lastPasswordResetDate, Clinic clinic, Time shiftStart, Time shiftEnd) {
		super(id, email, password, name, surname, enabled, lastPasswordResetDate, clinic, shiftStart, shiftEnd);
		// TODO Auto-generated constructor stub
	}

	public Nurse(String email, String password, String name, String surname, boolean enabled,
			Timestamp lastPasswordResetDate, Clinic clinic, Time shiftStart, Time shiftEnd) {
		super(email, password, name, surname, enabled, lastPasswordResetDate, clinic, shiftStart, shiftEnd);
		// TODO Auto-generated constructor stub
	}
	

}
