package com.ServisKlinickihCentara.repository;

import com.ServisKlinickihCentara.model.clinics.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic,Long> {
    Clinic findById(long id);

}
