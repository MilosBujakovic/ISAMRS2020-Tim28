package com.ServisKlinickihCentara.repository;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.employees.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Doctor findById(long id);

    ArrayList<Doctor> findAll();

}
