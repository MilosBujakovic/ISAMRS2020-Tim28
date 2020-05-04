package com.ServisKlinickihCentara.repository;

import com.ServisKlinickihCentara.model.clinics.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic,Long> {
    Clinic findById(long id);


    ArrayList<Clinic> findAll();

}
