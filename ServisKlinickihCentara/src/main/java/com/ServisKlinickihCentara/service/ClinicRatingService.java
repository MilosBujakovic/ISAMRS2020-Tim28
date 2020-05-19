package com.ServisKlinickihCentara.service;


import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.clinics.ClinicRating;
import com.ServisKlinickihCentara.model.patients.Patient;
import com.ServisKlinickihCentara.repository.ClinicRatingRepository;
import com.ServisKlinickihCentara.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicRatingService {

    @Autowired
    private ClinicRatingRepository clinicRatingRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private UserService userService;


    public MessageDTO evaluateClinicByPatient(String clinic_name, String email, String grade){
        if(grade.trim().equalsIgnoreCase("")){
            return new MessageDTO("You must enter number between 1 and 5!",false);
        }

        Patient patient = (Patient) userService.findByUsername(email);
        Clinic clinic = clinicRepository.findByName(clinic_name);
        ClinicRating clinicRating = clinicRatingRepository.findByClinicIdAndPatientId(clinic.getId(),patient.getId());

        if(clinicRating == null){
            clinicRating = new ClinicRating(clinic,patient);
        }
        clinicRating.setGrade(Double.parseDouble(grade));
        clinicRatingRepository.save(clinicRating);

        return new MessageDTO("You successfully evaluated clinic :)", true);
    }

}
