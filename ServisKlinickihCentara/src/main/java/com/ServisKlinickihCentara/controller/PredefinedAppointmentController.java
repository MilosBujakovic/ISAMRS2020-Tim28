package com.ServisKlinickihCentara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ServisKlinickihCentara.dto.appointmentsDTO.AppointmentTermDTO;
import com.ServisKlinickihCentara.service.PredefinedAppointmentService;

@RestController
@RequestMapping( value = "/clinicAdmin/predefinedAppointment")
public class PredefinedAppointmentController 
{
	
	@Autowired
	PredefinedAppointmentService appointmentService;
	
	@RequestMapping(value="/getTerms", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AppointmentTermDTO>> getTerms(@RequestBody AppointmentTermDTO appointmentSlot)
	{
		
		List<AppointmentTermDTO> terms = appointmentService.getTerms(appointmentSlot);
		System.out.println("Free Appointment Terms delivered!");
		return new ResponseEntity<List<AppointmentTermDTO>>(terms, HttpStatus.OK);
	}
}
