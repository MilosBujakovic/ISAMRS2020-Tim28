package com.ServisKlinickihCentara.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.patients.Appointment;

public interface PredefinedAppointmentRepository extends JpaRepository<Appointment,Long> 
{
	
}