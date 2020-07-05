package com.ServisKlinickihCentara.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.clinics.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic,Long> {
    Clinic findById(long id);

    Clinic findByName(String name);

    ArrayList<Clinic> findAll();
}
