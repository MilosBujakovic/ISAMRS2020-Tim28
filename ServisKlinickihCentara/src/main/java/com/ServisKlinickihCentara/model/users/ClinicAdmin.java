package com.ServisKlinickihCentara.model.users;

import javax.persistence.*;

import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.users.Admin;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Date;
import java.sql.Timestamp;


@Entity
@DiscriminatorValue("CLINICADMIN")
public class ClinicAdmin  extends Admin
{
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "clinic_admin_clinic",
			joinColumns =
					{ @JoinColumn(name = "user_id", referencedColumnName = "id") },
			inverseJoinColumns =
					{ @JoinColumn(name = "clinic_id", referencedColumnName = "id") })
	private Clinic clinic;
	
	@Column
	private boolean passwordChanged;
	
	public ClinicAdmin() 
	{
		super();
	}

	public ClinicAdmin(Long id, String email, String password, String name, String surname, boolean enabled,
			Timestamp lastPasswordResetDate, Clinic clinic) 
	{
		super(id, email, password, name, surname, enabled, lastPasswordResetDate);
		this.clinic = clinic;
		this.passwordChanged = false;
	}
	
	public ClinicAdmin(Long id, String email, String password, String name, String surname, boolean enabled,
			Timestamp lastPasswordResetDate, Clinic clinic, boolean passwordChanged) 
	{
		super(id, email, password, name, surname, enabled, lastPasswordResetDate);
		this.clinic = clinic;
		this.passwordChanged = passwordChanged;
	}


	public Clinic getClinic() 
	{
		return clinic;
	}

	public void setClinic(Clinic clinic) 
	{
		this.clinic = clinic;
	}

	public boolean isPasswordChanged() {
		return passwordChanged;
	}

	public void setPasswordChanged(boolean passwordChanged) {
		this.passwordChanged = passwordChanged;
	}
	
	
	
}
