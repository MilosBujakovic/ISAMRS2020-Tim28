package com.ServisKlinickihCentara.controller;


import com.ServisKlinickihCentara.dto.clinicsDTO.ClinicBasicFrontendDTO;
import com.ServisKlinickihCentara.dto.clinicsDTO.FilterClinicBasicDTO;
import com.ServisKlinickihCentara.model.enums.Specialty;
import com.ServisKlinickihCentara.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clinic")
public class ClinicController {

        @Autowired
        private ClinicService clinicService;


        @RequestMapping(value = "/getClinicsForBasicView", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ArrayList<ClinicBasicFrontendDTO>> getClinics(){
            ArrayList<ClinicBasicFrontendDTO> clinicBasicFrontendDTOS = clinicService.getClinics();
            System.out.println("getClinicsForBasicView");
            return new ResponseEntity<ArrayList<ClinicBasicFrontendDTO>>(clinicBasicFrontendDTOS, HttpStatus.OK);

        }


        @RequestMapping(value = "/basicFilterSortingClinics", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ArrayList<ClinicBasicFrontendDTO>> basicFilterSortingClinics(@RequestBody FilterClinicBasicDTO filterClinicBasicDTO){
            ArrayList<ClinicBasicFrontendDTO> clinicBasicFrontendDTOS = clinicService.basicFilterSortingClinics(filterClinicBasicDTO.getNameAddressSorting(),filterClinicBasicDTO.getSpeciality());
            System.out.println("basicFilterSortingClinics");
            return new ResponseEntity<ArrayList<ClinicBasicFrontendDTO>>(clinicBasicFrontendDTOS, HttpStatus.OK);
        }

        @RequestMapping(value = "/getSpecialities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ArrayList<String>> getSpecialities(){
            ArrayList<String>  specialities = Arrays.stream(Specialty.values()).map(Enum::name).collect(Collectors.toCollection(ArrayList::new));

            return new ResponseEntity<ArrayList<String>>(specialities, HttpStatus.OK);
        }





}
