package com.ServisKlinickihCentara.dto.appointmentsDTO;

public class HistoryVisitDTO {
    private String date;
    private String clinic;
    private String doctor;
    private String visitType;
    private String speciality;
    private String price;
    private String clinicGrade;
    private String doctorGrade;
    private String doctorId;

    public HistoryVisitDTO(){
        super();
    }

    public HistoryVisitDTO(String date, String clinic, String doctor, String visitType, String speciality, String price, String clinicGrade) {
        this.date = date;
        this.clinic = clinic;
        this.doctor = doctor;
        this.visitType = visitType;
        this.speciality = speciality;
        this.price = price;
        this.clinicGrade = clinicGrade;
    }

    public HistoryVisitDTO(String date, String clinic, String doctor, String visitType, String speciality, String price, String clinicGrade, String doctorGrade, String doctorId) {
        this.date = date;
        this.clinic = clinic;
        this.doctor = doctor;
        this.visitType = visitType;
        this.speciality = speciality;
        this.price = price;
        this.clinicGrade = clinicGrade;
        this.doctorGrade = doctorGrade;
        this.doctorId = doctorId;
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

    public String getClinicGrade() {
        return clinicGrade;
    }

    public void setClinicGrade(String clinicGrade) {
        this.clinicGrade = clinicGrade;
    }

    public String getDoctorGrade() {
        return doctorGrade;
    }

    public void setDoctorGrade(String doctorGrade) {
        this.doctorGrade = doctorGrade;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}
