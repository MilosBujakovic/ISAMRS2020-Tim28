package com.ServisKlinickihCentara.dto.medicalRecordsDTO;

import com.ServisKlinickihCentara.model.patients.MedicalRecord;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDTO {
    private String age;
    private String height;
    private String weight;
    private String diopter;
    private String alergies;
    private String bloodtype;
    private String rhfactor;
    private List<DiseaseHistoryDTO> diseaseHistoryDTOS;

    public MedicalRecordDTO(){
        super();
    }

    public MedicalRecordDTO(String age, String height, String weight, String diopter, String alergies, String bloodtype, String rhfactor) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.diopter = diopter;
        this.alergies = alergies;
        this.bloodtype = bloodtype;
        this.rhfactor = rhfactor;
        this.diseaseHistoryDTOS = new ArrayList<>();
    }


    public MedicalRecordDTO(MedicalRecord medicalRecord){
        this.age = String.valueOf(medicalRecord.getAge());
        this.height = String.valueOf(medicalRecord.getHeight());
        this.weight = String.valueOf(medicalRecord.getWeight());
        this.diopter = medicalRecord.getDiopter();
        this.alergies = medicalRecord.getAlergies();
        this.bloodtype = medicalRecord.getBloodtype().toString();
        this.rhfactor = medicalRecord.getRhfactor().toString();
        this.diseaseHistoryDTOS = new ArrayList<>();
    }

    public List<DiseaseHistoryDTO> getDiseaseHistoryDTOS() {
        return diseaseHistoryDTOS;
    }

    public void setDiseaseHistoryDTOS(List<DiseaseHistoryDTO> diseaseHistoryDTOS) {
        this.diseaseHistoryDTOS = diseaseHistoryDTOS;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDiopter() {
        return diopter;
    }

    public void setDiopter(String diopter) {
        this.diopter = diopter;
    }

    public String getAlergies() {
        return alergies;
    }

    public void setAlergies(String alergies) {
        this.alergies = alergies;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getRhfactor() {
        return rhfactor;
    }

    public void setRhfactor(String rhfactor) {
        this.rhfactor = rhfactor;
    }

    @Override
    public String toString() {
        return "MedicalRecordDTO{" +
                "age='" + age + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", diopter='" + diopter + '\'' +
                ", alergies='" + alergies + '\'' +
                ", bloodtype='" + bloodtype + '\'' +
                ", rhfactor='" + rhfactor + '\'' +
                '}';
    }
}
