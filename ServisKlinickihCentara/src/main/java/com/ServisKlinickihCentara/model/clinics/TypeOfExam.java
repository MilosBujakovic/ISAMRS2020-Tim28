package com.ServisKlinickihCentara.model.clinics;



import com.ServisKlinickihCentara.model.enums.Specialty;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class TypeOfExam {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Specialty specialty;

    @Column
    private int duration; //in minutes

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private PriceItem priceItem;

    public TypeOfExam(){
        super();
    }


    public TypeOfExam(Long id, String name, Specialty specialty, int duration, PriceItem priceItem) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.duration = duration;
        this.priceItem = priceItem;
    }

    public TypeOfExam(String name, Specialty specialty, int duration, PriceItem priceItem) {
        this.name = name;
        this.specialty = specialty;
        this.duration = duration;
        this.priceItem = priceItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public PriceItem getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(PriceItem priceItem) {
        this.priceItem = priceItem;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
