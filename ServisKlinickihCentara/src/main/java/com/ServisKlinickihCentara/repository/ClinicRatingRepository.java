package com.ServisKlinickihCentara.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.clinics.ClinicRating;

public interface ClinicRatingRepository extends JpaRepository<ClinicRating,Long> {

    ClinicRating findByClinicIdAndPatientId(Long clinicId, Long patientId);

}
