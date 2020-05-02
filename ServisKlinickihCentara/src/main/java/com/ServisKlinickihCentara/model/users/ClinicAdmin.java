package com.ServisKlinickihCentara.model.users;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.users.Admin;

import java.sql.Date;
import java.sql.Timestamp;


@Entity
@DiscriminatorValue("CLINICADMIN")
public class ClinicAdmin  extends Admin
{
	@Column
	private Clinic clinic;
	
	public ClinicAdmin() 
	{
		super();
	}

	public ClinicAdmin(Long id, String email, String password, String name, String surname, boolean enabled,
			Timestamp lastPasswordResetDate, Clinic clinic) 
	{
		super(id, email, password, name, surname, enabled, lastPasswordResetDate);
		this.clinic = clinic;
	}


	public Clinic getClinic() 
	{
		return clinic;
	}

	public void setClinic(Clinic clinic) 
	{
		this.clinic = clinic;
	}
	
	
}
