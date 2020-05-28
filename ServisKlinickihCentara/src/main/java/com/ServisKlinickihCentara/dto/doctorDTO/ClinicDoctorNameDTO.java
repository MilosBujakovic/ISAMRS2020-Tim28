package com.ServisKlinickihCentara.dto.doctorDTO;

public class ClinicDoctorNameDTO {
    private String clinicName;
    private String doctorNameSurname;

    public ClinicDoctorNameDTO(){
        super();
    }

    public ClinicDoctorNameDTO(String clinicName, String doctorNameSurname) {
        this.clinicName = clinicName;
        this.doctorNameSurname = doctorNameSurname;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getDoctorNameSurname() {
        return doctorNameSurname;
    }

    public void setDoctorNameSurname(String doctorNameSurname) {
        this.doctorNameSurname = doctorNameSurname;
    }
}
