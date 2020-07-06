package com.ServisKlinickihCentara.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.patients.AppointmentRequest;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest,Long>
{
    AppointmentRequest findById(long id);
}
