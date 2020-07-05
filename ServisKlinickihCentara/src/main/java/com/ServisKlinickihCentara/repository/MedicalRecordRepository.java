package com.ServisKlinickihCentara.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.patients.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {



}
