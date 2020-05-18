package com.ServisKlinickihCentara.controller;


import com.ServisKlinickihCentara.dto.doctorDTO.DoctorFreeSlotsViewDTO;
import com.ServisKlinickihCentara.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {


    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/getForClinicDoctorsFreeSlots", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<DoctorFreeSlotsViewDTO>> getDoctorsFreeSlots(@RequestParam String clinicId,@RequestParam String date){
        System.out.println("getForClinicDoctorsFreeSlots");
        ArrayList<DoctorFreeSlotsViewDTO> doctorFreeSlotsViewDTOS = doctorService.getForClinicDoctorsFreeSlots(clinicId,date);
        return new ResponseEntity<ArrayList<DoctorFreeSlotsViewDTO>>(doctorFreeSlotsViewDTOS, HttpStatus.OK);

    }

}
