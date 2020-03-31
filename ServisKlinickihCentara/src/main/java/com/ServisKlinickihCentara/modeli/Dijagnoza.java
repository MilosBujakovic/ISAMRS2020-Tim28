package com.ServisKlinickihCentara.modeli;

public class Dijagnoza 
{

	private String sifra;
	private String naziv;
	private String lijek;
	
	
	public Dijagnoza() {};		
	
	public Dijagnoza(String sifra, String naziv, String lijek) 
	{
		this.sifra = sifra;
		this.naziv = naziv;
		this.lijek = lijek;
	}



	public String getSifra() 
	{
		return sifra;
	}
	public void setSifra(String sifra) 
	{
		this.sifra = sifra;
	}
	public String getNaziv() 
	{
		return naziv;
	}
	public void setNaziv(String naziv) 
	{
		this.naziv = naziv;
	}
	public String getLijek() 
	{
		return lijek;
	}
	public void setLijek(String lijek) 
	{
		this.lijek = lijek;
	}
	
	
	
}
