package com.ServisKlinickihCentara.model.clinics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Diagnosis //TODO: finish later 
{

	@Id
	@Column
	private String id; //TODO: ne generisati automatski jer treba da ih koristimo u sifrarnicima (?)
	private String name;
	private String medication;
	
	
	public Diagnosis() {};		
	
	public Diagnosis(String id, String name, String medication) 
	{
		this.id = id;
		this.name = name;
		this.medication = medication;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getMedication() 
	{
		return medication;
	}

	public void setMedication(String medication) 
	{
		this.medication = medication;
	}

	
	
	
	
}
