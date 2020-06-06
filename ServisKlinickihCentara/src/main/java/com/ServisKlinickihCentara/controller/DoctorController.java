package com.ServisKlinickihCentara.controller;


import com.ServisKlinickihCentara.dto.doctorDTO.ClinicDoctorNameDTO;
import com.ServisKlinickihCentara.dto.doctorDTO.DoctorFreeSlotsViewDTO;
import com.ServisKlinickihCentara.dto.doctorDTO.DoctorSearchDTO;
import com.ServisKlinickihCentara.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {


    @Autowired
    private DoctorService doctorService;

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

}
