package com.ServisKlinickihCentara.dto;

public class PacijentDTO {
    private String email;
    private String lozinka1;
    private String lozinka2;
    private String ime;
    private String prezime;
    private String adresa;
    private String grad;
    private String drzava;
    private String broj_telefona;
    private String broj_osiguranika;

    public PacijentDTO(){

    }

    public PacijentDTO(String email, String lozinka1, String lozinka2, String ime, String prezime, String adresa, String grad, String drzava, String broj_telefona, String broj_osiguranika) {
        this.email = email;
        this.lozinka1 = lozinka1;
        this.lozinka2 = lozinka2;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.grad = grad;
        this.drzava = drzava;
        this.broj_telefona = broj_telefona;
        this.broj_osiguranika = broj_osiguranika;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka1() {
        return lozinka1;
    }

    public void setLozinka1(String lozinka1) {
        this.lozinka1 = lozinka1;
    }

    public String getLozinka2() {
        return lozinka2;
    }

    public void setLozinka2(String lozinka2) {
        this.lozinka2 = lozinka2;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getBroj_telefona() {
        return broj_telefona;
    }

    public void setBroj_telefona(String broj_telefona) {
        this.broj_telefona = broj_telefona;
    }

    public String getBroj_osiguranika() {
        return broj_osiguranika;
    }

    public void setBroj_osiguranika(String broj_osiguranika) {
        this.broj_osiguranika = broj_osiguranika;
    }

    @Override
    public String toString() {
        return "PacijentDTO{" +
                "email='" + email + '\'' +
                ", lozinka1='" + lozinka1 + '\'' +
                ", lozinka2='" + lozinka2 + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", adresa='" + adresa + '\'' +
                ", grad='" + grad + '\'' +
                ", drzava='" + drzava + '\'' +
                ", broj_telefona='" + broj_telefona + '\'' +
                ", broj_osiguranika='" + broj_osiguranika + '\'' +
                '}';
    }
}

