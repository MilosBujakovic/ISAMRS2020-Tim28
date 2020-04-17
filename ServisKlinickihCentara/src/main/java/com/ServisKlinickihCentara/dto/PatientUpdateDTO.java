package com.ServisKlinickihCentara.dto;

import com.ServisKlinickihCentara.model.Patient;

public class PatientUpdateDTO {
    private String email;
    private String name;
    private String surname;
    private String address;
    private String city;
    private String country;
    private String phone;


    public PatientUpdateDTO(){

    }

    public PatientUpdateDTO(String email, String name, String surname, String address, String city, String country, String phone) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }

    public PatientUpdateDTO(Patient patient){
        this.email = patient.getEmail();
        this.name = patient.getName();
        this.surname = patient.getSurname();
        this.address = patient.getAddress();
        this.city = patient.getCity();
        this.country = patient.getCountry();
        this.phone = patient.getPhoneNumber();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PatientUpdateDTO{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
