package com.ServisKlinickihCentara.service;


import com.ServisKlinickihCentara.dto.clinicsDTO.ClinicBasicFrontendDTO;
import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.clinics.ClinicRating;
import com.ServisKlinickihCentara.repository.ClinicRatingRepository;
import com.ServisKlinickihCentara.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ClinicRatingRepository clinicRatingRepository;



    public ArrayList<ClinicBasicFrontendDTO> getClinics(){
        ArrayList<ClinicBasicFrontendDTO> clinicBasicFrontendDTOS = new ArrayList<>();

        ArrayList<Clinic> clinics = clinicRepository.findAll();

        for(Clinic c: clinics){

            List<ClinicRating> clinicRatings = c.getClinicRatings();
            double rating = 0;
            if (clinicRatings != null){
                if (clinicRatings.size() > 0){

                    rating = clinicRatings.stream().collect(Collectors.averagingDouble(cr->cr.getGrade()));

                    /*double sum = 0;
                    for(ClinicRating clinicRating: clinicRatings) {
                        sum += clinicRating.getGrade();
                    }
                    rating = (int)sum/clinicRatings.size();*/
                }

            }

            ClinicBasicFrontendDTO cDTO = new ClinicBasicFrontendDTO(c.getId().toString(), c.getName(),c.getAddress(),c.getSpecialty().toString(),String.valueOf(rating));
            clinicBasicFrontendDTOS.add(cDTO);
        }
        return clinicBasicFrontendDTOS;

    }



    public ArrayList<ClinicBasicFrontendDTO> basicFilterSortingClinics(String nameAddressSorting, String speciality){
        ArrayList<ClinicBasicFrontendDTO> clinicBasicFrontendDTOS = new ArrayList<>();
        ArrayList<Clinic> clinics = clinicRepository.findAll();

        if(!nameAddressSorting.equalsIgnoreCase("")){
            if(nameAddressSorting.equalsIgnoreCase("name")){
                clinics.sort((Clinic c1, Clinic c2)->c1.getName().compareTo(c2.getName()));
            }else if(nameAddressSorting.equalsIgnoreCase("address")){
                clinics.sort((Clinic c1, Clinic c2)->c1.getAddress().compareTo(c2.getAddress()));
            }
        }



        if(!speciality.equalsIgnoreCase("")){
            clinics = clinics.stream()
                    .filter(clinic -> clinic.getSpecialty().toString().equalsIgnoreCase(speciality))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        for(Clinic c: clinics){
            List<ClinicRating> clinicRatings = c.getClinicRatings();
            double rating = 0;
            if (clinicRatings != null){
                if (clinicRatings.size() > 0){

                    rating = clinicRatings.stream().collect(Collectors.averagingDouble(cr->cr.getGrade()));
                }
            }
            clinicBasicFrontendDTOS.add(new ClinicBasicFrontendDTO(c.getId().toString(),c.getName(),
                    c.getAddress(),String.valueOf(rating)));
        }


        return clinicBasicFrontendDTOS;
    }


}
