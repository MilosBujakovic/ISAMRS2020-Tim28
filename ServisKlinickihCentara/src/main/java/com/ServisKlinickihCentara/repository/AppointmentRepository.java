package com.ServisKlinickihCentara.repository;

import com.ServisKlinickihCentara.model.patients.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    Appointment findById(long id);


    ArrayList<Appointment> findAll();


}
