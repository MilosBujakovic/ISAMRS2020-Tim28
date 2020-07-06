package com.ServisKlinickihCentara.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.employees.DoctorRating;

public interface DoctorRatingRepository extends JpaRepository<DoctorRating,Long> {

    DoctorRating findByDoctorIdAndPatientId(Long doctorId, Long patientId);

}
