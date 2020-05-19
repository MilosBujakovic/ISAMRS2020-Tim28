package com.ServisKlinickihCentara.dto.clinicsDTO;

import com.ServisKlinickihCentara.model.clinics.ClinicRating;

public class ClinicRatingDTO {
    private String clinicName;
    private String email;
    private String grade;

    public ClinicRatingDTO(){
        super();
    }

    public ClinicRatingDTO(String clinicName, String email, String grade) {
        this.clinicName = clinicName;
        this.email = email;
        this.grade = grade;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
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
        return "ClinicRatingDTO{" +
                "clinicName='" + clinicName + '\'' +
                ", email='" + email + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
