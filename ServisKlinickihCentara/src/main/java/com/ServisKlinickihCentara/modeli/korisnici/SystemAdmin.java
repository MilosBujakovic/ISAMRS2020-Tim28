package com.ServisKlinickihCentara.modeli.korisnici;

import com.ServisKlinickihCentara.modeli.enumeracije.TipKorisnika;

public class SystemAdmin extends Korisnik
{
	
	private static SystemAdmin instance;
	
	public SystemAdmin getInstance()
	{
		if(instance==null)
		{
			instance = new SystemAdmin();
		}
		return instance;
			
	}
	
	private SystemAdmin()
	{
		this.setEmail("admin");
		this.setLozinka("admin");
		this.setIme("Admin");
		this.setTipKorisnika(TipKorisnika.Admin);
		this.setAktivan(true);
		
	}
}
