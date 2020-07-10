package com.ServisKlinickihCentara.dto.clinicsDTO;

public class RoomDTO {
    private String id;
    private String clinicId;
    private String number;

    public RoomDTO(){

    }

    public RoomDTO(String id, String clinicId, String number) {
        this.id = id;
        this.clinicId = clinicId;
        this.number = number;
    }

    public RoomDTO(String clinicId, String number) {
        this.clinicId = clinicId;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
