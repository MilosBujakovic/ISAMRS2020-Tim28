package com.ServisKlinickihCentara.controller;


import com.ServisKlinickihCentara.service.TypeOfExamService;
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
@RequestMapping(value = "/typeOfExam")
public class TypeOfExamController {

    @Autowired
    private TypeOfExamService typeOfExamService;

    @RequestMapping(value = "/getTypeOfExams", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<String>> getTypeOfExams(){
        ArrayList<String> names = typeOfExamService.getTypeOfExams();
        return new ResponseEntity<ArrayList<String>>(names, HttpStatus.OK);

    }

    @RequestMapping(value = "/getTypeOfExamsWithoutOperations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<String>> getTypeOfExamsWithoutOperations(){
        ArrayList<String> names = typeOfExamService.getTypeOfExamsWithoutOperations();
        return new ResponseEntity<ArrayList<String>>(names, HttpStatus.OK);

    }
    
    @RequestMapping(value = "/getTypeOfExamsForClinic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<String>> getTypeOfExamsForClinic(@RequestParam long clinicId){
    	
    	System.out.println("_"+clinicId+"_");
        ArrayList<String> names = typeOfExamService.getTypeOfExamsForClinic(clinicId);
        return new ResponseEntity<ArrayList<String>>(names, HttpStatus.OK);

    }

}
