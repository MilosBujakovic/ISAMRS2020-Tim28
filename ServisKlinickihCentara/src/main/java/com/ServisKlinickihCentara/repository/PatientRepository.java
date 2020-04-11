package com.ServisKlinickihCentara.repository;


import com.ServisKlinickihCentara.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    List<Patient> findAll();

    Patient findByEmail(String email);

    Patient findByPhoneNumber(String phoneNumber);

    Patient findByInsuranceNumber(String insuranceNumber);



}
