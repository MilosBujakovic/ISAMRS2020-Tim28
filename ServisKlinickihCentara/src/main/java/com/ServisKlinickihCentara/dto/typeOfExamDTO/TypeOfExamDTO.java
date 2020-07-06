package com.ServisKlinickihCentara.dto.typeOfExamDTO;

public class TypeOfExamDTO {
    private String name;
    private String specialty;
    private String duration;
    private String appointmentType;
    private String price;
    private String discount;

    public TypeOfExamDTO(){
        super();
    }

    public TypeOfExamDTO(String name, String specialty, String duration, String appointmentType, String price, String discount) {
        super();
        this.name = name;
        this.specialty = specialty;
        this.duration = duration;
        this.appointmentType = appointmentType;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }


}
