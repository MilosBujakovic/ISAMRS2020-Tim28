package com.ServisKlinickihCentara.dto.clinicsDTO;

public class FilterClinicBasicDTO {
    private String nameAddressSorting;
    private String speciality;

    public FilterClinicBasicDTO(){
        super();
    }

    public FilterClinicBasicDTO(String nameAddressSorting, String speciality) {
        this.nameAddressSorting = nameAddressSorting;
        this.speciality = speciality;
    }

    public String getNameAddressSorting() {
        return nameAddressSorting;
    }

    public void setNameAddressSorting(String nameAddressSorting) {
        this.nameAddressSorting = nameAddressSorting;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
