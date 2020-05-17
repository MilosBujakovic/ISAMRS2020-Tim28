package com.ServisKlinickihCentara.dto.clinicsDTO;

import java.util.ArrayList;
import java.util.List;

public class FilterExistingAsiDTO {
    //private ClinicIdsDTO ids;
    private String speciality;
    private String rating;

    public FilterExistingAsiDTO(){
        super();
    }

    public FilterExistingAsiDTO(String speciality, String rating) {
        //this.ids = ids;
        this.speciality = speciality;
        this.rating = rating;
    }

    /*public ClinicIdsDTO getIds() {
        return ids;
    }

    public void setIds(ClinicIdsDTO ids) {
        this.ids = ids;
    }*/

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
