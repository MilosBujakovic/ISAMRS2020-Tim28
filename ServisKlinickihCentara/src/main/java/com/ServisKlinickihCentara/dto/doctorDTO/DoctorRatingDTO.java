package com.ServisKlinickihCentara.dto.doctorDTO;

public class DoctorRatingDTO {
    private String doctorId;
    private String email;
    private String grade;

    public DoctorRatingDTO(){
        super();
    }

    public DoctorRatingDTO(String doctorId, String email, String grade) {
        this.doctorId = doctorId;
        this.email = email;
        this.grade = grade;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "DoctorRatingDTO{" +
                "doctorId='" + doctorId + '\'' +
                ", email='" + email + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
