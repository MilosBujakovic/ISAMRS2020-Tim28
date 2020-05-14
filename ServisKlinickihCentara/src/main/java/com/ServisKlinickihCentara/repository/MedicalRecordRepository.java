package com.ServisKlinickihCentara.repository;

import com.ServisKlinickihCentara.model.patients.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {



}
