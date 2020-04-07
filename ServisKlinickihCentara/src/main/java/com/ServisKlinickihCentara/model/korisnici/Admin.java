package com.ServisKlinickihCentara.model.korisnici;

import com.ServisKlinickihCentara.model.enumeracije.TipAdmina;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Admin extends Korisnik {


    @Column(nullable = false)
    private TipAdmina tipAdmina;

    public Admin(){

    }

    public Admin(Long id, String email, String lozinka, String ime, String prezime, boolean aktivan) {
        super(id, email, lozinka, ime, prezime, aktivan);
    }
}
