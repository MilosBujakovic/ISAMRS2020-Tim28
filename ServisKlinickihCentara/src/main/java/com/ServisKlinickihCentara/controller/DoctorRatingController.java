package com.ServisKlinickihCentara.controller;


import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.doctorDTO.DoctorRatingDTO;
import com.ServisKlinickihCentara.service.DoctorRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/doctorRating")
public class DoctorRatingController {

    @Autowired
    private DoctorRatingService doctorRatingService;


    @RequestMapping(value = "/evaluateDoctorByPatient", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> evaluateDoctorByPatient(@RequestBody DoctorRatingDTO doctorRatingDTO){
        System.out.println("evaluateDoctorByPatient");
        System.out.println(doctorRatingDTO);
        MessageDTO messageDTO = doctorRatingService.evaluateDoctorByPatient(doctorRatingDTO.getDoctorId(),doctorRatingDTO.getEmail(),doctorRatingDTO.getGrade());
        return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);

    }


}
