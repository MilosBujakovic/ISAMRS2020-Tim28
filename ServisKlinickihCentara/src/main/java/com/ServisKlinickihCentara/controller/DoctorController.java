package com.ServisKlinickihCentara.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ServisKlinickihCentara.dto.doctorDTO.ClinicDoctorNameDTO;
import com.ServisKlinickihCentara.dto.doctorDTO.DoctorFreeSlotsViewDTO;
import com.ServisKlinickihCentara.dto.doctorDTO.DoctorSearchDTO;
import com.ServisKlinickihCentara.dto.patientsDTO.PatientFilterViewDTO;
import com.ServisKlinickihCentara.service.DoctorService;
import com.ServisKlinickihCentara.service.PatientService;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {


    @Autowired
    private DoctorService doctorService;
    
    @Autowired 
    PatientService patientService;

    @RequestMapping(value = "/getForClinicDoctorsFreeSlots", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<DoctorFreeSlotsViewDTO>> getDoctorsFreeSlots(@RequestParam String clinicId,@RequestParam String date, @RequestParam String te){
        System.out.println("getForClinicDoctorsFreeSlots");
        ArrayList<DoctorFreeSlotsViewDTO> doctorFreeSlotsViewDTOS = doctorService.getForClinicDoctorsFreeSlots(clinicId,date,te);
        return new ResponseEntity<ArrayList<DoctorFreeSlotsViewDTO>>(doctorFreeSlotsViewDTOS, HttpStatus.OK);

    }

    @RequestMapping(value = "/filterExistingDoctors", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<DoctorFreeSlotsViewDTO>> filterExistingDoctors(@RequestParam List<String> ids, @RequestBody DoctorSearchDTO doctorSearchDTO, @RequestParam String te){
        System.out.println("filterExistingDoctors");
        ArrayList<DoctorFreeSlotsViewDTO> doctorFreeSlotsViewDTOS = doctorService.filterExistingDoctors(ids,doctorSearchDTO,te);
        return new ResponseEntity<ArrayList<DoctorFreeSlotsViewDTO>>(doctorFreeSlotsViewDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/getClinicAndDoctor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicDoctorNameDTO> getClinicAndDoctor(@RequestParam String clinicId, @RequestParam String doctorId){
        System.out.println("getClinicAndDoctor");
        ClinicDoctorNameDTO clinicDoctorNameDTO = doctorService.getClinicAndDoctor(clinicId,doctorId);
        return new ResponseEntity<ClinicDoctorNameDTO>(clinicDoctorNameDTO, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/filterClinicPatients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientFilterViewDTO>> filterClinicPatients(@RequestParam String clinicId , @RequestParam String name,
    		@RequestParam String surname, @RequestParam String insuranceNumber, @RequestParam String city){
        System.out.println("get filtered clinic's patients");
        List<PatientFilterViewDTO> clinicDoctorNameDTO = patientService.filterPatients(clinicId,name,surname, insuranceNumber, city);
        System.out.println("Patients criteria successfully filtered!");
        return new ResponseEntity<List<PatientFilterViewDTO>>(clinicDoctorNameDTO, HttpStatus.OK);
    }
    
    

}
