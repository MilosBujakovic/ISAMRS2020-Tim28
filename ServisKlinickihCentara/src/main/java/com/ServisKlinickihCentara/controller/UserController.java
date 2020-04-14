package com.ServisKlinickihCentara.controller;


import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.PatientDTO;
import com.ServisKlinickihCentara.dto.UnregisteredPatientDTO;
import com.ServisKlinickihCentara.model.Authority;
import com.ServisKlinickihCentara.model.Patient;
import com.ServisKlinickihCentara.model.User;
import com.ServisKlinickihCentara.service.EmailService;
import com.ServisKlinickihCentara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/user")
//@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> register (@RequestBody PatientDTO patientDTO){
        System.out.println(patientDTO);
        MessageDTO messageDTO = userService.registratePatient(patientDTO);
        System.out.println(messageDTO);
        return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/acceptPatient/{email}", method = RequestMethod.PUT)
    public ResponseEntity acceptPatient(@PathVariable String email){
        System.out.println(email);
        userService.sendActivationLink(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/rejectPatient/{email}/{message}", method = RequestMethod.PUT)
    public ResponseEntity rejectPatient(@PathVariable String email,@PathVariable String message){
        userService.deletePatient(email);
        emailService.sendMail(email,"Your request for registration has been denied!!!",message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getUnregisteredPatients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<UnregisteredPatientDTO>> getUnregisteredPatients(){
        ArrayList<UnregisteredPatientDTO> unregisteredPatients = userService.getUnregisteredPatients();
        return new ResponseEntity<ArrayList<UnregisteredPatientDTO>>(unregisteredPatients,HttpStatus.OK);
    }

    @RequestMapping(value = "/activatePatient/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<String> activatePatient(@PathVariable("uuid") String uuid){
        userService.setPatientActive(uuid);
        return new ResponseEntity<String>("Your profile has been activated, click <a href=\'http://localhost:8080\'> here </a> to login on system.",HttpStatus.OK);
    }

}