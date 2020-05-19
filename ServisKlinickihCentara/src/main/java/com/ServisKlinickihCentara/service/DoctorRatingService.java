package com.ServisKlinickihCentara.service;


import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.model.employees.Doctor;
import com.ServisKlinickihCentara.model.employees.DoctorRating;
import com.ServisKlinickihCentara.model.patients.Patient;
import com.ServisKlinickihCentara.repository.DoctorRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorRatingService {

    @Autowired
    private DoctorRatingRepository doctorRatingRepository;

    @Autowired
    private UserService userService;

    public MessageDTO evaluateDoctorByPatient(String doctor_id, String email, String grade){
        if(grade.trim().equalsIgnoreCase("")){
            return new MessageDTO("You must enter number between 1 and 5!",false);
        }

        Patient patient = (Patient) userService.findByUsername(email);
        Doctor doctor = (Doctor) userService.findById(Long.parseLong(doctor_id));
        DoctorRating doctorRating = doctorRatingRepository.findByDoctorIdAndPatientId(Long.parseLong(doctor_id),patient.getId());


        if(doctorRating == null){
            doctorRating = new DoctorRating(doctor,patient);
        }
        doctorRating.setGrade(Double.parseDouble(grade));
        doctorRatingRepository.save(doctorRating);

        return new MessageDTO("You successfully evaluated doctor :)", true);
    }

}
