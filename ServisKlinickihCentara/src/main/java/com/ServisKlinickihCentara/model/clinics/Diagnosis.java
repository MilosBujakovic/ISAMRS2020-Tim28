package com.ServisKlinickihCentara.model.clinics;

import javax.persistence.*;

@Entity
public class Diagnosis //TODO: finish later 
{

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //TODO: ne generisati automatski jer treba da ih koristimo u sifrarnicima (?)

	@Column
	private String name;

	@Column
	private String medication;
	
	
	public Diagnosis() {};		
	
	public Diagnosis(long id, String name, String medication)
	{
		this.id = id;
		this.name = name;
		this.medication = medication;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(long id)
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
