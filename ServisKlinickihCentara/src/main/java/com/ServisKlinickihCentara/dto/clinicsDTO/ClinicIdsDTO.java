package com.ServisKlinickihCentara.dto.clinicsDTO;

import java.util.List;

public class ClinicIdsDTO {
    private List<String> clinicsIds;

    public ClinicIdsDTO(){
        super();
    }

    public ClinicIdsDTO(List<String> clinicsIds) {
        this.clinicsIds = clinicsIds;
    }

    public List<String> getClinicsIds() {
        return clinicsIds;
    }

    public void setClinicsIds(List<String> clinicsIds) {
        this.clinicsIds = clinicsIds;
    }
}
