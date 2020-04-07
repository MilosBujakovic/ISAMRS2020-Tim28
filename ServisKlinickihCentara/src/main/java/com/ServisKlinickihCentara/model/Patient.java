package com.ServisKlinickihCentara.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends User{

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String phone_number;

    @Column
    private String insurance_number;

    public Patient(){
        super();
    }

    public Patient(Long id, String email, String password, String name, String surname, boolean enabled, Timestamp lastPasswordResetDate, String address, String city, String country, String phone_number, String insurance_number) {
        super(id, email, password, name, surname, enabled, lastPasswordResetDate);
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone_number = phone_number;
        this.insurance_number = insurance_number;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getInsurance_number() {
        return insurance_number;
    }

    public void setInsurance_number(String insurance_number) {
        this.insurance_number = insurance_number;
    }
}
