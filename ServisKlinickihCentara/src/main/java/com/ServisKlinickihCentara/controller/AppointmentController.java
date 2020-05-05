package com.ServisKlinickihCentara.controller;


import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.appointmentsDTO.AppointmentPreDTO;
import com.ServisKlinickihCentara.dto.appointmentsDTO.PredefinedAppointmenViewtDTO;
import com.ServisKlinickihCentara.model.enums.Specialty;
import com.ServisKlinickihCentara.model.patients.Appointment;
import com.ServisKlinickihCentara.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/quickAppointmentReservation", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> quickAppointmentReservation(@RequestBody AppointmentPreDTO appointmentPreDTO){
        System.out.println("quickAppointmentReservation");
        MessageDTO messageDTO = appointmentService.quickAppointmentReservation(appointmentPreDTO.getEmail(),appointmentPreDTO.getAppointmentId());
        return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/acceptQuickAppointment/{uuid}/{appointmentId}/{appointmentRequestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> acceptQuickAppointment(@PathVariable("uuid") String uuid,
                                                             @PathVariable("appointmentId") String appointmentId,
                                                             @PathVariable("appointmentRequestId") String appointmentRequestId){
        System.out.println("acceptQuickAppointment");
        MessageDTO messageDTO = appointmentService.acceptQuickAppointment(uuid,appointmentId,appointmentRequestId);
        return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);
    }


    @RequestMapping(value = "/declineQuickAppointment/{uuid}/{appointmentRequestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> declineQuickAppointment(@PathVariable("uuid") String uuid,
                                                             @PathVariable("appointmentRequestId") String appointmentRequestId){
        System.out.println("declineQuickAppointment");
        MessageDTO messageDTO = appointmentService.declineQuickAppointment(uuid,appointmentRequestId);
        return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);
    }


}
