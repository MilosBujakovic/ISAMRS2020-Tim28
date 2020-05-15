package com.ServisKlinickihCentara.controller;


import com.ServisKlinickihCentara.dto.EmailDTO;
import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.appointmentsDTO.*;
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


    @RequestMapping(value = "/getPatientsAppointments", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<ReservedAppointmentDTO>> getPatientsAppointments(@RequestBody EmailDTO emailDTO){
        System.out.println("getPatientsAppointments");
        ArrayList<ReservedAppointmentDTO> reservedAppointmentDTOS = appointmentService.getPatientsAppointments(emailDTO.getEmail());
        return new ResponseEntity<ArrayList<ReservedAppointmentDTO>>(reservedAppointmentDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/cancelAppointment/{appointmentId}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> cancelAppointment(@PathVariable("appointmentId") String appointmentId){
        System.out.println("cancelAppointment");
        MessageDTO messageDTO = appointmentService.cancelAppointment(appointmentId);
        return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/getPatientsHistory", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<HistoryVisitDTO>> getPatientsHistory(@RequestBody EmailDTO emailDTO){
        System.out.println("getPatientsHistory");
        ArrayList<HistoryVisitDTO> historyVisitDTOS = appointmentService.getPatientsHistory(emailDTO.getEmail());
        return new ResponseEntity<ArrayList<HistoryVisitDTO>>(historyVisitDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/filterSortingPatientsHistory", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<HistoryVisitDTO>> filterSortingPatientsHistory(@RequestBody HistoryVisitFilterSortDTO historyVisitFilterSortDTO){
        System.out.println("filterSortingPatientsHistory");
        ArrayList<HistoryVisitDTO> historyVisitDTOS = appointmentService.filterSortingPatientsHistory(historyVisitFilterSortDTO);
        return new ResponseEntity<ArrayList<HistoryVisitDTO>>(historyVisitDTOS, HttpStatus.OK);
    }


}
