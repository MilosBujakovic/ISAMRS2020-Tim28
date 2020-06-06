package com.ServisKlinickihCentara.dto.clinicsDTO;

import java.util.ArrayList;
import java.util.List;

public class FilterExistingAsiDTO {
    private String typeOfExam;
    private String rating;
    private String sortBy;

    public FilterExistingAsiDTO(){
        super();
    }


    public FilterExistingAsiDTO(String typeOfExam, String rating, String sortBy) {
        this.typeOfExam = typeOfExam;
        this.rating = rating;
        this.sortBy = sortBy;
    }


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

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
