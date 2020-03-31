package com.ServisKlinickihCentara.modeli.korisnici;

import com.ServisKlinickihCentara.modeli.enumeracije.TipKorisnika;

public class Korisnik 
{
	private String email;
	private String lozinka;
	private String ime;
	private String prezime;
	private TipKorisnika tipKorisnika;
	private boolean aktivan;
	
	
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
	
	public TipKorisnika getTipKorisnika()
	{
		return this.tipKorisnika;
	}
	
	public void setTipKorisnika(TipKorisnika tipKorisnika)
	{
		this.tipKorisnika = tipKorisnika;
	}
};
