package com.ServisKlinickihCentara.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ServisKlinickihCentara.dto.clinicsDTO.EditClinicDTO;

@RestController
@RequestMapping( value = "/clinicEdit")
public class ClinicEditController 
{
	@RequestMapping(value="/updateBasic", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveBasicSettings(@RequestBody EditClinicDTO clinicDTO)
	{
		System.out.println(clinicDTO.getAdmin()+" | "+clinicDTO.getName());
		System.out.println("Changes saved!");
		
	}
}
