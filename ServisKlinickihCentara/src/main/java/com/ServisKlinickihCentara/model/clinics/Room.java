package com.ServisKlinickihCentara.model.clinics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room
{
	@Id
	@Column
	public Long number;
	
	@Column
	public boolean active;
	
	@Column
	public boolean booked;
	
	
	public Room(Long broj) 
	{
		this.number = broj;
		this.active = true;
		this.booked = false;
	};
	
	public Room(Long broj, boolean aktivna, boolean zauzeta) 
	{
		this.number = broj;
		this.active = aktivna;
		this.booked = zauzeta;
	}
	
	
	public Long getBroj() 
	{
		return number;
	}
	public void setBroj(Long broj) 
	{
		this.number = broj;
	}
	public boolean isAktivna() 
	{
		return active;
	}
	public void setAktivna(boolean aktivna) 
	{
		this.active = aktivna;
	}
	public boolean getZauzeta() 
	{
		return booked;
	}
	public void setZauzeta(boolean zauzeta) 
	{
		this.booked = zauzeta;
	}
	
	
}
