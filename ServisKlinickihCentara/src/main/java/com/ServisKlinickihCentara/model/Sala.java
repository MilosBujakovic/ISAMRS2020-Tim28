package com.ServisKlinickihCentara.model;

public class Sala 
{
	public int broj;
	public boolean aktivna;
	public boolean zauzeta;
	
	
	public Sala(int broj) 
	{
		this.broj = broj;
		this.aktivna = true;
		this.zauzeta = false;
	};
	
	public Sala(int broj, boolean aktivna, boolean zauzeta) 
	{
		this.broj = broj;
		this.aktivna = aktivna;
		this.zauzeta = zauzeta;
	}
	
	
	public int getBroj() 
	{
		return broj;
	}
	public void setBroj(int broj) 
	{
		this.broj = broj;
	}
	public boolean isAktivna() 
	{
		return aktivna;
	}
	public void setAktivna(boolean aktivna) 
	{
		this.aktivna = aktivna;
	}
	public boolean getZauzeta() 
	{
		return zauzeta;
	}
	public void setZauzeta(boolean zauzeta) 
	{
		this.zauzeta = zauzeta;
	}
	
	
}
