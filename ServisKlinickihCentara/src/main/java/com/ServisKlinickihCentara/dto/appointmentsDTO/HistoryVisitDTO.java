package com.ServisKlinickihCentara.dto.appointmentsDTO;

public class HistoryVisitDTO {
    private String date;
    private String clinic;
    private String doctor;
    private String visitType;
    private String speciality;
    private String price;

    public HistoryVisitDTO(){
        super();
    }

    public HistoryVisitDTO(String date, String clinic, String doctor, String visitType, String speciality, String price) {
        this.date = date;
        this.clinic = clinic;
        this.doctor = doctor;
        this.visitType = visitType;
        this.speciality = speciality;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "HistoryVisitDTO{" +
                "date='" + date + '\'' +
                ", clinic='" + clinic + '\'' +
                ", doctor='" + doctor + '\'' +
                ", visitType='" + visitType + '\'' +
                ", speciality='" + speciality + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
