package com.ServisKlinickihCentara.dto.clinicsDTO;

public class AdvancedSearchClinicDTO {
    private String date;
    private String typeOfExam;
    private String address;
    private String rating;

    public  AdvancedSearchClinicDTO(){
        super();
    }


    public AdvancedSearchClinicDTO(String date, String typeOfExam, String address, String rating) {
        this.date = date;
        this.typeOfExam = typeOfExam;
        this.address = address;
        this.rating = rating;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeOfExam() {
        return typeOfExam;
    }

    public void setTypeOfExam(String typeOfExam) {
        this.typeOfExam = typeOfExam;
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
