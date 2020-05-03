package com.ServisKlinickihCentara.model.clinics;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column
	private String number;
	
	/*@Column
	public boolean active;
	
	@Column
	public boolean booked;*/

	@ManyToOne
	@JsonManagedReference
	private Clinic clinic;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "room")
	@JsonBackReference
	private List<Term> termList;


	public Room(){
		super();
	}

	public Room(Long id, String number, Clinic clinic, List<Term> termList) {
		this.id = id;
		this.number = number;
		this.clinic = clinic;
		this.termList = termList;
	}

	public Room(Long id, String number, Clinic clinic) {
		this.id = id;
		this.number = number;
		this.clinic = clinic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public List<Term> getTermList() {
		return termList;
	}

	public void setTermList(List<Term> termList) {
		this.termList = termList;
	}


	/*public boolean isAktivna()
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
	}*/
	
	
}
