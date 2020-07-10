package com.ServisKlinickihCentara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ServisKlinickihCentara.dto.patientsDTO.PatientRecordViewDTO;
import com.ServisKlinickihCentara.service.PatientService;

@RestController
@RequestMapping(value = "/patient")
public class PatientController 
{

	@Autowired
	PatientService patientService;
	
	
    @RequestMapping(value = "/getMedicalRecords", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientRecordViewDTO> filterClinicPatients(@RequestParam String patientId){
        System.out.println("get Patient medical records");
        PatientRecordViewDTO patientRecord = patientService.getMedicalRecords(patientId);
        System.out.println("Patient's records delivered!");
        return new ResponseEntity<PatientRecordViewDTO>(patientRecord, HttpStatus.OK);
    }
	
}
