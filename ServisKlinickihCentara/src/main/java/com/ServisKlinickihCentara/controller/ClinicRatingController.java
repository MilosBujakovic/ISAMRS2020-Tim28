package com.ServisKlinickihCentara.controller;


import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.clinicsDTO.ClinicRatingDTO;
import com.ServisKlinickihCentara.model.clinics.ClinicRating;
import com.ServisKlinickihCentara.service.ClinicRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/clinicRating")
public class ClinicRatingController {

    @Autowired
    private ClinicRatingService clinicRatingService;

    @RequestMapping(value = "/evaluateClinicByPatient", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> evaluateClinicByPatient(@RequestBody ClinicRatingDTO clinicRatingDTO){
        System.out.println("evaluateClinicByPatient");
        System.out.println(clinicRatingDTO);
        MessageDTO messageDTO = clinicRatingService.evaluateClinicByPatient(clinicRatingDTO.getClinicName(),clinicRatingDTO.getEmail(),clinicRatingDTO.getGrade());
        return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);

    }

}
