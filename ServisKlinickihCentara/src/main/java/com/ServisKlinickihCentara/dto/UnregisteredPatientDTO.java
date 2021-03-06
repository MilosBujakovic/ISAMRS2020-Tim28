package com.ServisKlinickihCentara.dto;

import com.ServisKlinickihCentara.model.patients.Patient;

public class UnregisteredPatientDTO {
    private String email;
    private String name;
    private String surname;
    private String address;
    private String city;
    private String country;
    private String phone;
    private String insurance_number;

    public UnregisteredPatientDTO(){

    }

    public UnregisteredPatientDTO(Patient patient) {
        this.email = patient.getEmail();
        this.name = patient.getName();
        this.surname = patient.getSurname();
        this.address = patient.getAddress();
        this.city = patient.getCity();
        this.country = patient.getCountry();
        this.phone = patient.getPhoneNumber();
        this.insurance_number = patient.getInsuranceNumber();
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

    public String getInsurance_number() {
        return insurance_number;
    }

    public void setInsurance_number(String insurance_number) {
        this.insurance_number = insurance_number;
    }
}
