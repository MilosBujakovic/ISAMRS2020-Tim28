package com.ServisKlinickihCentara.model.clinics;


import com.ServisKlinickihCentara.repository.DoctorRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorRating {

    @Autowired
    private DoctorRatingRepository doctorRatingRepository;



}
