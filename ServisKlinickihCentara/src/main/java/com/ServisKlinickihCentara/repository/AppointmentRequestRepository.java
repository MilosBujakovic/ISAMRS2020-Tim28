package com.ServisKlinickihCentara.repository;


import com.ServisKlinickihCentara.model.patients.AppointmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest,Long>
{
    AppointmentRequest findById(long id);
}
