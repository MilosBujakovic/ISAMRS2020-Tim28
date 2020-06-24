package com.ServisKlinickihCentara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ServisKlinickihCentara.dto.EmailDTO;
import com.ServisKlinickihCentara.dto.clinicsDTO.EditClinicDTO;
import com.ServisKlinickihCentara.service.ClinicEditService;

@RestController
@RequestMapping( value = "/clinicAdmin/clinicEdit")
public class ClinicEditController 
{
	@Autowired
	ClinicEditService clinicService;
	
	@RequestMapping(value="/updateBasics", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveBasicSettings(@RequestBody EditClinicDTO clinicDTO)
	{
		clinicService.updateClinicBasics(clinicDTO);
		System.out.println("Changes saved!");
		
	}
	
	@RequestMapping(value="/getClinicData", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EditClinicDTO> getClinicData(@RequestBody EmailDTO email)
	{
		 EditClinicDTO clinic = clinicService.getClinicBasics(email.getEmail());
		 System.out.println("Admin's clinic found!");
		 return new ResponseEntity<EditClinicDTO>(clinic, HttpStatus.OK);
	}
}
