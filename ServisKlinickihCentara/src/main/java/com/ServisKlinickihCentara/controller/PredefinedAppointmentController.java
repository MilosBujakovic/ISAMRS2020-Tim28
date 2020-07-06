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

import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.appointmentsDTO.AppointmentTermDTO;
import com.ServisKlinickihCentara.dto.appointmentsDTO.MakePredefinedAppointmentDTO;
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
	
	@RequestMapping(value="/makeAppointment", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageDTO> makePredefinedAppointment(@RequestBody MakePredefinedAppointmentDTO predefinedAppointment)
	{
		
		boolean success = appointmentService.makeAppointment(predefinedAppointment);
		if(success)
		{
			System.out.println("Predefined appointment created!");//TODO: AppointmentType = CHECKUP
			MessageDTO msg = new MessageDTO("Predefined appointment successfully made!", success);
			return new ResponseEntity<MessageDTO>(msg, HttpStatus.CREATED);
		}
		else 
		{
			System.out.println("Error encountered! Try Again!");
			MessageDTO msg = new MessageDTO("Error encountered! Try Again!", success);
			return new ResponseEntity<MessageDTO>(msg, HttpStatus.BAD_REQUEST);
		}
	}
}
