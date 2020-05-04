package com.ServisKlinickihCentara.controller;


import com.ServisKlinickihCentara.dto.appointmentsDTO.PredefinedAppointmenViewtDTO;
import com.ServisKlinickihCentara.model.enums.Specialty;
import com.ServisKlinickihCentara.model.patients.Appointment;
import com.ServisKlinickihCentara.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @RequestMapping(value = "/getPredefinedAppointments/{clinicId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PredefinedAppointmenViewtDTO>> getPredefinedAppointments(@PathVariable("clinicId") String clinicId){
        System.out.println("getPredefinedAppointments");
        List<PredefinedAppointmenViewtDTO> appointments = appointmentService.getPredefinedAppointments(clinicId);
        return new ResponseEntity<List<PredefinedAppointmenViewtDTO>>(appointments, HttpStatus.OK);
    }

}
