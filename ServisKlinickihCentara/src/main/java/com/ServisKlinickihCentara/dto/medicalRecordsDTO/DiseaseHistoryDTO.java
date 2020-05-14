package com.ServisKlinickihCentara.dto.medicalRecordsDTO;

import java.util.ArrayList;
import java.util.List;

public class DiseaseHistoryDTO {
    private String date;
    private String desease;
    private String treatment;
    private String description;
    private List<PrescriptionMrDTO> prescriptionMrDTOS;

    public DiseaseHistoryDTO(){
        super();
    }

    public DiseaseHistoryDTO(String date, String desease, String treatment, String description) {
        this.date = date;
        this.desease = desease;
        this.treatment = treatment;
        this.description = description;
        this.prescriptionMrDTOS = new ArrayList<>();
    }

    public DiseaseHistoryDTO(String date, String desease, String treatment, String description, List<PrescriptionMrDTO> prescriptionMrDTOS) {
        this.date = date;
        this.desease = desease;
        this.treatment = treatment;
        this.description = description;
        this.prescriptionMrDTOS = prescriptionMrDTOS;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesease() {
        return desease;
    }

    public void setDesease(String desease) {
        this.desease = desease;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<PrescriptionMrDTO> getPrescriptionMrDTOS() {
        return prescriptionMrDTOS;
    }

    public void setPrescriptionMrDTOS(List<PrescriptionMrDTO> prescriptionMrDTOS) {
        this.prescriptionMrDTOS = prescriptionMrDTOS;
    }

    @Override
    public String toString() {
        return "DiseaseHistoryDTO{" +
                "date='" + date + '\'' +
                ", desease='" + desease + '\'' +
                ", treatment='" + treatment + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
