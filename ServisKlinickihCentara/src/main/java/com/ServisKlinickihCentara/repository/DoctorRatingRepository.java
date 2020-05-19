package com.ServisKlinickihCentara.repository;

import com.ServisKlinickihCentara.model.employees.DoctorRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRatingRepository extends JpaRepository<DoctorRating,Long> {

    DoctorRating findByDoctorIdAndPatientId(Long doctorId, Long patientId);

}
