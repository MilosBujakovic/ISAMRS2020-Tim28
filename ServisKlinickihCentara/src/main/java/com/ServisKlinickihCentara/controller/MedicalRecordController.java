package com.ServisKlinickihCentara.controller;


import com.ServisKlinickihCentara.dto.EmailDTO;
import com.ServisKlinickihCentara.dto.medicalRecordsDTO.MedicalRecordDTO;
import com.ServisKlinickihCentara.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/medicalRecord")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @RequestMapping(value = "/getMedicalRecord", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalRecordDTO> getMedicalRecord(@RequestBody EmailDTO emailDTO){
        System.out.println("getMedicalRecord");
        MedicalRecordDTO medicalRecordDTO = medicalRecordService.getMedicalRecord(emailDTO.getEmail());
        System.out.println(medicalRecordDTO);
        return new ResponseEntity<MedicalRecordDTO>(medicalRecordDTO, HttpStatus.OK);
    }
}
