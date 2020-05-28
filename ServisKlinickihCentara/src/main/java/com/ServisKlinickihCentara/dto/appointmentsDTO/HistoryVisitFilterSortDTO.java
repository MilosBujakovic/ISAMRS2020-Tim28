package com.ServisKlinickihCentara.dto.appointmentsDTO;

public class HistoryVisitFilterSortDTO {
    private String email;
    private String visitType;
    private String typeOfExam;
    private String sortingType;

    public HistoryVisitFilterSortDTO(){
        super();
    }

    public HistoryVisitFilterSortDTO(String email, String visitType, String typeOfExam, String sortingType) {
        this.email = email;
        this.visitType = visitType;
        this.typeOfExam = typeOfExam;
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

    public String getTypeOfExam() {
        return typeOfExam;
    }

    public void setTypeOfExam(String typeOfExam) {
        this.typeOfExam = typeOfExam;
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
                ", typeOfExam='" + typeOfExam + '\'' +
                ", sortingType='" + sortingType + '\'' +
                '}';
    }
}
