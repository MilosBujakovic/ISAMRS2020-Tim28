package com.ServisKlinickihCentara.controller;

import java.sql.Time;
import java.util.ArrayList;
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
import com.ServisKlinickihCentara.model.clinics.TypeOfExam;
import com.ServisKlinickihCentara.service.TypeOfExamService;

@RestController
@RequestMapping( value = "/clinicAdmin/predefinedAppointment")
public class PredefinedAppointmentController 
{
	@Autowired
	TypeOfExamService examsService;
	
	@Autowired
	PredefinedAppointmentService appointmentService;
	
	@RequestMapping(value="/getTerms", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AppointmentTermDTO>> getTerms(@RequestBody AppointmentTermDTO appointmentSlot)
	{
		System.out.println("USaO U getTerms");
		List<AppointmentTermDTO> terms = new ArrayList<AppointmentTermDTO>();
		Time startTime = Time.valueOf(appointmentSlot.getStartTime() );
		Time finalTime = Time.valueOf(appointmentSlot.getEndTime() );
		int i = 0;
		AppointmentTermDTO term = new AppointmentTermDTO();
		TypeOfExam te = examsService.getExamType(appointmentSlot.getTypeOfExam());
		do
		{
			i++;
			System.out.println(startTime);
			term.setStartTime(startTime.toString());
			startTime.setMinutes(startTime.getMinutes()+te.getDuration());
			term.setEndTime(startTime.toString());
			System.out.println(startTime);
			
		}
		while(startTime.before(finalTime));
		
		return new ResponseEntity<List<AppointmentTermDTO>>(terms, HttpStatus.OK);
	}
}
