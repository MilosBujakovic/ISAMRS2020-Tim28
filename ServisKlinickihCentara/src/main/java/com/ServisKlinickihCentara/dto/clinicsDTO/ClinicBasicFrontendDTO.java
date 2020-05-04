package com.ServisKlinickihCentara.dto.clinicsDTO;


public class ClinicBasicFrontendDTO {
    private String id;
    private String name;
    private String address;
    private String speciality;
    private String average_rating;


    public ClinicBasicFrontendDTO(){
        super();
    }

    public ClinicBasicFrontendDTO(String id, String name, String address, String speciality) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.speciality = speciality;
    }

    public ClinicBasicFrontendDTO(String id, String name, String address, String speciality, String average_rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.speciality = speciality;
        this.average_rating = average_rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(String average_rating) {
        this.average_rating = average_rating;
    }
}
