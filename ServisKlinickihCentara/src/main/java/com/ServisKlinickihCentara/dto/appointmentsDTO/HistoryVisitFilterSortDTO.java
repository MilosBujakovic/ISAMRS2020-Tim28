package com.ServisKlinickihCentara.dto.appointmentsDTO;

public class HistoryVisitFilterSortDTO {
    private String email;
    private String visitType;
    private String speciality;
    private String sortingType;

    public HistoryVisitFilterSortDTO(){
        super();
    }

    public HistoryVisitFilterSortDTO(String email, String visitType, String speciality, String sortingType) {
        this.email = email;
        this.visitType = visitType;
        this.speciality = speciality;
        this.sortingType = sortingType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getSortingType() {
        return sortingType;
    }

    public void setSortingType(String sortingType) {
        this.sortingType = sortingType;
    }

    @Override
    public String toString() {
        return "HistoryVisitFilterSortDTO{" +
                "email='" + email + '\'' +
                ", visitType='" + visitType + '\'' +
                ", speciality='" + speciality + '\'' +
                ", sortingType='" + sortingType + '\'' +
                '}';
    }
}
