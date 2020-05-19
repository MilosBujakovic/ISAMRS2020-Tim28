package com.ServisKlinickihCentara.dto.doctorDTO;

public class DoctorSearchDTO {
    private String name;
    private String surname;
    private String rating;
    private String date;

    public DoctorSearchDTO(){
        super();
    }

    public DoctorSearchDTO(String name, String surname, String rating) {
        this.name = name;
        this.surname = surname;
        this.rating = rating;
    }

    public DoctorSearchDTO(String name, String surname, String rating, String date) {
        this.name = name;
        this.surname = surname;
        this.rating = rating;
        this.date = date;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

