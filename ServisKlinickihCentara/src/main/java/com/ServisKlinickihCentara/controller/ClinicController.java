package com.ServisKlinickihCentara.controller;


import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.clinicsDTO.*;
import com.ServisKlinickihCentara.model.enums.Specialty;
import com.ServisKlinickihCentara.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clinic")
public class ClinicController {

        @Autowired
        private ClinicService clinicService;


        @RequestMapping(value = "/getClinicNameAddressRating/{clinicId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ClinicBasicFrontendDTO> getClinicNameAddressRating(@PathVariable("clinicId") String clinicId){
            ClinicBasicFrontendDTO clinicBasicFrontendDTO = clinicService.getClinicNameAddressRating(Long.parseLong(clinicId));
            System.out.println("getClinicNameAddressRating");
            return new ResponseEntity<ClinicBasicFrontendDTO>(clinicBasicFrontendDTO, HttpStatus.OK);

        }

        @RequestMapping(value = "/getClinicsForBasicView", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ArrayList<ClinicBasicFrontendDTO>> getClinics(){
            ArrayList<ClinicBasicFrontendDTO> clinicBasicFrontendDTOS = clinicService.getClinics();
            System.out.println("getClinicsForBasicView");
            return new ResponseEntity<ArrayList<ClinicBasicFrontendDTO>>(clinicBasicFrontendDTOS, HttpStatus.OK);

        }


        @RequestMapping(value = "/basicFilterSortingClinics", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ArrayList<ClinicBasicFrontendDTO>> basicFilterSortingClinics(@RequestBody FilterClinicBasicDTO filterClinicBasicDTO){
            ArrayList<ClinicBasicFrontendDTO> clinicBasicFrontendDTOS = clinicService.basicFilterSortingClinics(filterClinicBasicDTO.getNameAddressSorting());
            System.out.println("basicFilterSortingClinics");
            return new ResponseEntity<ArrayList<ClinicBasicFrontendDTO>>(clinicBasicFrontendDTOS, HttpStatus.OK);
        }

        @RequestMapping(value = "/getSpecialities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ArrayList<String>> getSpecialities(){
            ArrayList<String>  specialities = Arrays.stream(Specialty.values()).map(Enum::name).collect(Collectors.toCollection(ArrayList::new));

            return new ResponseEntity<ArrayList<String>>(specialities, HttpStatus.OK);
        }



        @RequestMapping(value = "/getClinicsByAdvancedSearch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ArrayList<AdvancedSearchItem>> getClinicsByAdvancedSearch(@RequestBody AdvancedSearchClinicDTO advancedSearchClinicDTO){
            System.out.println("getClinicsByAdvancedSearch");
            ArrayList<AdvancedSearchItem> advancedSearchItems = clinicService.getClinicsByAdvancedSearch(advancedSearchClinicDTO);
            return new ResponseEntity<ArrayList<AdvancedSearchItem>>(advancedSearchItems, HttpStatus.OK);
        }

        @RequestMapping(value = "/filterExistingAdvancedSearchedItems", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ArrayList<AdvancedSearchItem>> filterExistingAdvancedSearchedItems(@RequestParam List<String> ids, @RequestBody FilterExistingAsiDTO filterExistingAsiDTO){
            System.out.println("filterExistingAdvancedSearchedItems");
            ArrayList<AdvancedSearchItem> advancedSearchItems = clinicService.filterExistingAdvancedSearchedItems(ids,filterExistingAsiDTO);
            return new ResponseEntity<ArrayList<AdvancedSearchItem>>(advancedSearchItems, HttpStatus.OK);
        }

        @RequestMapping(value = "/checkClinicHasFreeDoctorsForSpecificDateAndTypeOfExam/{clinicId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<MessageDTO> checkClinicHasFreeDoctorsForSpecificDateAndTypeOfExam(@PathVariable("clinicId") String clinicId ,@RequestBody AdvancedSearchClinicDTO advancedSearchClinicDTO){
            System.out.println("checkClinicHasFreeDoctorsForSpecificDateAndTypeOfExam");
            MessageDTO messageDTO = clinicService.checkClinicHasFreeDoctorsForSpecificDateAndTypeOfExam(Long.parseLong(clinicId),advancedSearchClinicDTO);
            return new ResponseEntity<MessageDTO>(messageDTO, HttpStatus.OK);
        }

}
