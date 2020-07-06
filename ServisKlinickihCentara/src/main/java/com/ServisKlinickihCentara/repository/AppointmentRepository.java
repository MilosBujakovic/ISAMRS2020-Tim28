package com.ServisKlinickihCentara.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.patients.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    Appointment findById(long id);


    ArrayList<Appointment> findAll();


}
