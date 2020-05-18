package com.ServisKlinickihCentara.dto.doctorDTO;

import java.util.ArrayList;

public class DoctorFreeSlotsViewDTO {
    private String name;
    private String surname;
    private String rating;
    private ArrayList<ShiftDTO> freeSlots;

    public DoctorFreeSlotsViewDTO(){
        super();
    }

    public DoctorFreeSlotsViewDTO(String name, String surname, String rating, ArrayList<ShiftDTO> freeSlots) {
        this.name = name;
        this.surname = surname;
        this.rating = rating;
        this.freeSlots = freeSlots;
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

    public ArrayList<ShiftDTO> getFreeSlots() {
        return freeSlots;
    }

    public void setFreeSlots(ArrayList<ShiftDTO> freeSlots) {
        this.freeSlots = freeSlots;
    }
}
