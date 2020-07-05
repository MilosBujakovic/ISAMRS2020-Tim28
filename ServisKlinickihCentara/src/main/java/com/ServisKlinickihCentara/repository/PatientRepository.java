package com.ServisKlinickihCentara.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.patients.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    List<Patient> findAll();

    Patient findByEmail(String email);

    Patient findByPhoneNumber(String phoneNumber);

    Patient findByInsuranceNumber(String insuranceNumber);



}
