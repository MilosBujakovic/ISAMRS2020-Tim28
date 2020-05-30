package com.ServisKlinickihCentara.dto.clinicsDTO;

import java.util.ArrayList;
import java.util.List;

public class FilterExistingAsiDTO {
    //private ClinicIdsDTO ids;
    private String typeOfExam;
    private String rating;

    public FilterExistingAsiDTO(){
        super();
    }

    public FilterExistingAsiDTO(String typeOfExam, String rating) {
        //this.ids = ids;
        this.typeOfExam = typeOfExam;
        this.rating = rating;
    }

    /*public ClinicIdsDTO getIds() {
        return ids;
    }

    public void setIds(ClinicIdsDTO ids) {
        this.ids = ids;
    }*/

    public String getTypeOfExam() {
        return typeOfExam;
    }

    public void setTypeOfExam(String typeOfExam) {
        this.typeOfExam = typeOfExam;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
