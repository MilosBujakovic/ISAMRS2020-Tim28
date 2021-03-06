package com.ServisKlinickihCentara.dto.appointmentsDTO;

public class ReservedAppointmentDTO {
    private String id;
    private String timestamp;
    private String room;
    private String doctor;
    private String typeSpeciality;
    private String price;
    private String discount;
    private String status;

    public ReservedAppointmentDTO(){
        super();
    }

    public ReservedAppointmentDTO(String id, String timestamp, String room, String doctor, String typeSpeciality, String price, String status) {
        this.id = id;
        this.timestamp = timestamp;
        this.room = room;
        this.doctor = doctor;
        this.typeSpeciality = typeSpeciality;
        this.price = price;
        this.status = status;
    }

    public ReservedAppointmentDTO(String id, String timestamp, String room, String doctor, String typeSpeciality, String price, String discount, String status) {
        this.id = id;
        this.timestamp = timestamp;
        this.room = room;
        this.doctor = doctor;
        this.typeSpeciality = typeSpeciality;
        this.price = price;
        this.discount = discount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getTypeSpeciality() {
        return typeSpeciality;
    }

    public void setTypeSpeciality(String typeSpeciality) {
        this.typeSpeciality = typeSpeciality;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
