package com.ServisKlinickihCentara.dto.clinicsDTO;

public class FilterClinicBasicDTO {
    private String nameAddressSorting;

    public FilterClinicBasicDTO(){
        super();
    }

    public FilterClinicBasicDTO(String nameAddressSorting) {
        this.nameAddressSorting = nameAddressSorting;
    }

    public String getNameAddressSorting() {
        return nameAddressSorting;
    }

    public void setNameAddressSorting(String nameAddressSorting) {
        this.nameAddressSorting = nameAddressSorting;
    }

}
