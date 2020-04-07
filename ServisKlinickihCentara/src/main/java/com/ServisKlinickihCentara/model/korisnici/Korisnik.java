package com.ServisKlinickihCentara.model.korisnici;

import javax.persistence.*;


@Entity
@Inheritance
public class Korisnik
{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;


	@Column(unique = true)
	private String email;

	@Column
	private String lozinka;

	@Column
	private String ime;

	@Column
	private String prezime;
	//private TipKorisnika tipKorisnika;

	@Column
	private boolean aktivan;

	public Korisnik(){

	}

	public Korisnik(Long id, String email, String lozinka, String ime, String prezime, boolean aktivan) {
		this.id = id;
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.aktivan = aktivan;
	}

	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getLozinka() 
	{
		return lozinka;
	}
	public void setLozinka(String lozinka) 
	{
		this.lozinka = lozinka;
	}
	public String getIme() 
	{
		return ime;
	}
	public void setIme(String ime) 
	{
		this.ime = ime;
	}
	public String getPrezime() 
	{
		return prezime;
	}
	public void setPrezime(String prezime) 
	{
		this.prezime = prezime;
	}

	public boolean getAktivan()
	{
		return this.aktivan;
	}
	
	public void setAktivan(boolean aktivan)
	{
		this.aktivan = aktivan;
	}
	
	/*public TipKorisnika getTipKorisnika()
	{
		return this.tipKorisnika;
	}
	
	public void setTipKorisnika(TipKorisnika tipKorisnika)
	{
		this.tipKorisnika = tipKorisnika;
	}*/
};
