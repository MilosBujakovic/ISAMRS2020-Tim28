package com.ServisKlinickihCentara.repository;


import com.ServisKlinickihCentara.model.employees.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Doctor findById(long id);


}
