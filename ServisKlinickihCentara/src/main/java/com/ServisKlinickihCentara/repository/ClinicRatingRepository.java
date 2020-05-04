package com.ServisKlinickihCentara.repository;

import com.ServisKlinickihCentara.model.clinics.ClinicRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ClinicRatingRepository extends JpaRepository<ClinicRating,Long> {

    ArrayList<ClinicRating> findByClinicId(Long clinic_id);

}
