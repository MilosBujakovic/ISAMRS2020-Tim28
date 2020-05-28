package com.ServisKlinickihCentara.service;


import com.ServisKlinickihCentara.dto.clinicsDTO.AdvancedSearchClinicDTO;
import com.ServisKlinickihCentara.dto.clinicsDTO.AdvancedSearchItem;
import com.ServisKlinickihCentara.dto.clinicsDTO.ClinicBasicFrontendDTO;
import com.ServisKlinickihCentara.dto.clinicsDTO.FilterExistingAsiDTO;
import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.clinics.ClinicRating;
import com.ServisKlinickihCentara.model.employees.Doctor;
import com.ServisKlinickihCentara.model.employees.LeaveForm;
import com.ServisKlinickihCentara.repository.ClinicRatingRepository;
import com.ServisKlinickihCentara.repository.ClinicRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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

            ClinicBasicFrontendDTO cDTO = new ClinicBasicFrontendDTO(c.getId().toString(), c.getName(),c.getAddress(),String.valueOf(rating));
            clinicBasicFrontendDTOS.add(cDTO);
        }
        return clinicBasicFrontendDTOS;

    }



    public ArrayList<ClinicBasicFrontendDTO> basicFilterSortingClinics(String nameAddressSorting){
        ArrayList<ClinicBasicFrontendDTO> clinicBasicFrontendDTOS = new ArrayList<>();
        ArrayList<Clinic> clinics = clinicRepository.findAll();

        if(!nameAddressSorting.equalsIgnoreCase("")){
            if(nameAddressSorting.equalsIgnoreCase("name")){
                clinics.sort((Clinic c1, Clinic c2)->c1.getName().compareTo(c2.getName()));
            }else if(nameAddressSorting.equalsIgnoreCase("address")){
                clinics.sort((Clinic c1, Clinic c2)->c1.getAddress().compareTo(c2.getAddress()));
            }
        }



       /* if(!speciality.equalsIgnoreCase("")){
            clinics = clinics.stream()
                    .filter(clinic -> clinic.getSpecialty().toString().equalsIgnoreCase(speciality))
                    .collect(Collectors.toCollection(ArrayList::new));
        }*/

        for(Clinic c: clinics){
            List<ClinicRating> clinicRatings = c.getClinicRatings();
            double rating = 0;
            if (clinicRatings != null){
                if (clinicRatings.size() > 0){

                    rating = clinicRatings.stream().collect(Collectors.averagingDouble(cr->cr.getGrade()));
                }
            }
            clinicBasicFrontendDTOS.add(new ClinicBasicFrontendDTO(c.getId().toString(),c.getName(),
                    c.getAddress(), String.valueOf(rating)));
        }


        return clinicBasicFrontendDTOS;
    }

    public ArrayList<AdvancedSearchItem> getClinicsByAdvancedSearch(AdvancedSearchClinicDTO advancedSearchClinicDTO) {
        ArrayList<AdvancedSearchItem> advancedSearchItems = new ArrayList<>();
        ArrayList<Clinic> clinics = clinicRepository.findAll();


        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.toLocalDateTime().toLocalDate());

        LocalDate localDate = LocalDate.parse(advancedSearchClinicDTO.getDate());

        for (Clinic clinic : clinics) {

            if (!clinic.getAddress().toLowerCase().contains(advancedSearchClinicDTO.getAddress().toLowerCase()) &&
                    !clinic.getAddress().equalsIgnoreCase("")) {
                continue;
            }

            /*if (!clinic.getSpecialty().toString().equalsIgnoreCase(advancedSearchClinicDTO.getSpeciality()) &&
                    !advancedSearchClinicDTO.getSpeciality().equalsIgnoreCase("")) {
                continue;
            }*/

            double rating = 0;
            rating = clinic.getClinicRatings().stream().collect(Collectors.averagingDouble(cr -> cr.getGrade()));


            if(!advancedSearchClinicDTO.getRating().equalsIgnoreCase("")){
                if ((int) rating != Integer.parseInt(advancedSearchClinicDTO.getRating())) {
                    continue;
                }
            }

            List<Doctor> doctors = clinic.getStaff();
            //doctors = doctors.stream().filter(doctor -> doctor.getSpecialty().toString().equalsIgnoreCase(advancedSearchClinicDTO.getSpeciality())).collect(Collectors.toCollection(ArrayList::new));

            if(doctors.size() == 0){
                continue;
            }

            boolean doctorIsFree = true;
            for (Doctor doctor : doctors) {
                LocalDate today = LocalDate.now();
                List<LeaveForm> vacations = doctor.getVacations()
                        .stream().filter(leaveForm -> leaveForm.getStartDate().isAfter(today))
                        .collect(Collectors.toCollection(ArrayList::new));

                if (vacations.size() > 0) {
                    for (LeaveForm vacation : vacations) {
                        LocalDate vacationStartDate = vacation.getStartDate();
                        LocalDate vacationEndDate = vacation.getEndDate();

                        boolean isBetweenOrEqual = this.dateIsBetweenOrEqual(localDate, vacationStartDate, vacationEndDate);

                        if (isBetweenOrEqual) {
                            doctorIsFree = false;
                            break;
                        }

                    }
                } else {
                    doctorIsFree = true;
                    break;
                }
            }
            if (doctorIsFree) {
                String ratingDto = "";
                if (rating == 0) {
                    ratingDto = "No rating";
                } else {
                    ratingDto = String.valueOf(rating);
                }
                AdvancedSearchItem a = new AdvancedSearchItem(clinic.getId().toString(), clinic.getName(), ratingDto, clinic.getAddress(),"", "price");
                advancedSearchItems.add(a);
            }

        }
        return advancedSearchItems;
    }


    public ArrayList<AdvancedSearchItem> filterExistingAdvancedSearchedItems(List<String> ids, FilterExistingAsiDTO filterExistingAsiDTO) {
        ArrayList<AdvancedSearchItem> advancedSearchItems = new ArrayList<>();
        ArrayList<Clinic> clinics = clinicRepository.findAll();
        clinics = clinics.stream().filter(clinic -> ids.contains(clinic.getId().toString())).collect(Collectors.toCollection(ArrayList::new));

        /*if(!filterExistingAsiDTO.getSpeciality().equalsIgnoreCase("")){
            clinics = clinics.stream()
                    .filter(clinic -> clinic.getSpecialty().toString().equalsIgnoreCase(filterExistingAsiDTO.getSpeciality()))
                    .collect(Collectors.toCollection(ArrayList::new));
        }*/

        HashMap<Long,Double> ratings = new HashMap<>();
        for(Clinic clinic: clinics){
            double clinicRating = clinic.getClinicRatings().stream().collect(Collectors.averagingDouble(cr -> cr.getGrade()));
            ratings.put(clinic.getId(),clinicRating);
        }

        if(!filterExistingAsiDTO.getRating().equalsIgnoreCase("")){
            double ratingDTO = Double.parseDouble(filterExistingAsiDTO.getRating());
            clinics = clinics.stream().filter(clinic -> ratings.get(clinic.getId()) == ratingDTO).collect(Collectors.toCollection(ArrayList::new));
        }


        advancedSearchItems = clinics.stream()
                .map(clinic -> new AdvancedSearchItem(clinic.getId().toString(), clinic.getName(), String.valueOf(ratings.get(clinic.getId())), clinic.getAddress(),"", "price"))
                .collect(Collectors.toCollection(ArrayList::new));

        return advancedSearchItems;
    }

    public boolean dateIsBetweenOrEqual(LocalDate date, LocalDate startDate, LocalDate endDate){
        if((date.isAfter(startDate) && date.isBefore(endDate)) || date.equals(startDate) || date.equals(endDate)){
            return true;
        }
        return false;
    }
}
