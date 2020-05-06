package com.ServisKlinickihCentara.dto.clinicsDTO;

public class AdvancedSearchClinicDTO {
    private String date;
    private String speciality;
    private String address;
    private String rating;

    public  AdvancedSearchClinicDTO(){
        super();
    }


    public AdvancedSearchClinicDTO(String date, String speciality, String address, String rating) {
        this.date = date;
        this.speciality = speciality;
        this.address = address;
        this.rating = rating;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
