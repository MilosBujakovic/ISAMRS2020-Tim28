package com.ServisKlinickihCentara.dto.appointmentsDTO;

public class CustomAppointmentDTO {
    private String email;
    private String clinicId;
    private String doctorId;
    private String date;
    private String typeOfExam;
    private String startTime;

    public CustomAppointmentDTO(){
        super();
    }

    public CustomAppointmentDTO(String email, String clinicId, String doctorId, String date, String typeOfExam, String startTime) {
        this.email = email;
        this.clinicId = clinicId;
        this.doctorId = doctorId;
        this.date = date;
        this.typeOfExam = typeOfExam;
        this.startTime = startTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "CustomAppointmentDTO{" +
                "email='" + email + '\'' +
                ", clinicId='" + clinicId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", date='" + date + '\'' +
                ", typeOfExam='" + typeOfExam + '\'' +
                ", startTime='" + startTime + '\'' +
                '}';
    }
}
