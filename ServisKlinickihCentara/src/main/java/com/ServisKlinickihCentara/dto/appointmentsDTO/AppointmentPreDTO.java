package com.ServisKlinickihCentara.dto.appointmentsDTO;

public class AppointmentPreDTO {
    private String email;
    private String appointmentId;

    public AppointmentPreDTO(){
        super();
    }

    public AppointmentPreDTO(String email, String appointmentId) {
        this.email = email;
        this.appointmentId = appointmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
}
