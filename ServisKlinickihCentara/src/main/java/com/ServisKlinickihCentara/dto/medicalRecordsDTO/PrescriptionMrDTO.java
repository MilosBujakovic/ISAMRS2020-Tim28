package com.ServisKlinickihCentara.dto.medicalRecordsDTO;

public class PrescriptionMrDTO {
    private String name;
    private String amount;

    public PrescriptionMrDTO(){
        super();
    }

    public PrescriptionMrDTO(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PrescriptionMrDTO{" +
                "name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
