package com.ServisKlinickihCentara.service;


import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.clinicsDTO.AdvancedSearchClinicDTO;
import com.ServisKlinickihCentara.dto.clinicsDTO.AdvancedSearchItem;
import com.ServisKlinickihCentara.dto.clinicsDTO.ClinicBasicFrontendDTO;
import com.ServisKlinickihCentara.dto.clinicsDTO.FilterExistingAsiDTO;
import com.ServisKlinickihCentara.dto.doctorDTO.DoctorFreeSlotsViewDTO;
import com.ServisKlinickihCentara.dto.doctorDTO.ShiftDTO;
import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.clinics.ClinicRating;
import com.ServisKlinickihCentara.model.clinics.TypeOfExam;
import com.ServisKlinickihCentara.model.employees.Doctor;
import com.ServisKlinickihCentara.model.employees.LeaveForm;
import com.ServisKlinickihCentara.repository.ClinicRatingRepository;
import com.ServisKlinickihCentara.repository.ClinicRepository;
import com.ServisKlinickihCentara.repository.TypeOfExamRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
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

    @Autowired
    private TypeOfExamRepository typeOfExamRepository;

    @Autowired
    private DoctorService doctorService;


    public ClinicBasicFrontendDTO getClinicNameAddressRating(long id){
        Clinic clinic = clinicRepository.findById(id);

        double rating = clinic.getClinicRatings()
                .stream().collect(Collectors.averagingDouble(cr->cr.getGrade()));

        String ratingDTO = "No rating";
        if(rating != 0){
            ratingDTO = String.valueOf(rating);
        }
        return new ClinicBasicFrontendDTO(String.valueOf(id),clinic.getName(),clinic.getAddress(),ratingDTO);
    }


    public ArrayList<ClinicBasicFrontendDTO> getClinics(){
        ArrayList<ClinicBasicFrontendDTO> clinicBasicFrontendDTOS = new ArrayList<>();

        ArrayList<Clinic> clinics = clinicRepository.findAll();

        for(Clinic c: clinics){

            List<ClinicRating> clinicRatings = c.getClinicRatings();
            double rating = 0;
            String clinicRatingDTO = "No rating";
            if (clinicRatings != null){
                if (clinicRatings.size() > 0){
                    rating = clinicRatings.stream().collect(Collectors.averagingDouble(cr->cr.getGrade()));
                    clinicRatingDTO = String.valueOf(rating);
                }

            }

            ClinicBasicFrontendDTO cDTO = new ClinicBasicFrontendDTO(c.getId().toString(), c.getName(),c.getAddress(),clinicRatingDTO);
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
            }else if(nameAddressSorting.equalsIgnoreCase("rating")){
                clinics.sort((Clinic c1, Clinic c2)->c1.getClinicRatings()
                        .stream().collect(Collectors.averagingDouble(cr -> cr.getGrade()))
                        .compareTo(c2.getClinicRatings().stream().collect(Collectors.averagingDouble(cr -> cr.getGrade()))));
            }
        }


        for(Clinic c: clinics){
            List<ClinicRating> clinicRatings = c.getClinicRatings();
            double rating = 0;
            String clinicRatingDTO = "No rating";
            if (clinicRatings != null){
                if (clinicRatings.size() > 0){

                    rating = clinicRatings.stream().collect(Collectors.averagingDouble(cr->cr.getGrade()));
                    clinicRatingDTO = String.valueOf(rating);
                }
            }
            clinicBasicFrontendDTOS.add(new ClinicBasicFrontendDTO(c.getId().toString(),c.getName(),
                    c.getAddress(), clinicRatingDTO));
        }


        return clinicBasicFrontendDTOS;
    }

    public ArrayList<AdvancedSearchItem> getClinicsByAdvancedSearch(AdvancedSearchClinicDTO advancedSearchClinicDTO) {
        ArrayList<AdvancedSearchItem> advancedSearchItems = new ArrayList<>();
        ArrayList<Clinic> clinics = clinicRepository.findAll();

        TypeOfExam te = typeOfExamRepository.findByName(advancedSearchClinicDTO.getTypeOfExam());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.toLocalDateTime().toLocalDate());

        LocalDate localDate = LocalDate.parse(advancedSearchClinicDTO.getDate());

        for (Clinic clinic : clinics) {

            if (!clinic.getAddress().toLowerCase().contains(advancedSearchClinicDTO.getAddress().trim().toLowerCase()) &&
                    !advancedSearchClinicDTO.getAddress().equalsIgnoreCase("")) {
                continue;
            }

            boolean typeOfExamExists = clinic.getTypeOfExams()
                    .stream().anyMatch(typeOfExam -> typeOfExam.getName().equalsIgnoreCase(advancedSearchClinicDTO.getTypeOfExam()));
            if(!typeOfExamExists){
                continue;
            }


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

            boolean doctorIsntOnVacation = true;
            boolean doctorHasFreeTerms = false;
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
                            doctorIsntOnVacation = false;
                            break;
                        }

                    }
                } else {
                    doctorIsntOnVacation = true;
                }

                if(doctorIsntOnVacation){
                    doctorHasFreeTerms = doctorService.findFreeDoctorSlotsForSpecificDate(doctor,localDate,te).size() > 0;
                }

                if(doctorHasFreeTerms){
                    break;
                }

            }
            if (doctorHasFreeTerms) {

                String ratingDto = "";
                if (rating == 0) {
                    ratingDto = "No rating";
                } else {
                    ratingDto = String.valueOf(rating);
                }
                AdvancedSearchItem a = new AdvancedSearchItem(clinic.getId().toString(), clinic.getName(), ratingDto, clinic.getAddress(), String.valueOf(te.getPriceItem().getPrice()));
                advancedSearchItems.add(a);
            }

        }
        return advancedSearchItems;
    }


    public ArrayList<AdvancedSearchItem> filterExistingAdvancedSearchedItems(List<String> ids, FilterExistingAsiDTO filterExistingAsiDTO) {
        ArrayList<AdvancedSearchItem> advancedSearchItems = new ArrayList<>();
        ArrayList<Clinic> clinics = clinicRepository.findAll();
        clinics = clinics.stream().filter(clinic -> ids.contains(clinic.getId().toString())).collect(Collectors.toCollection(ArrayList::new));

        TypeOfExam te = typeOfExamRepository.findByName(filterExistingAsiDTO.getTypeOfExam());

        /*if(!filterExistingAsiDTO.getTypeOfExam().equalsIgnoreCase("")){
            clinics = clinics.stream().filter(clinic -> clinic.getTypeOfExams()
                    .stream().anyMatch(typeOfExam -> typeOfExam.getName().equalsIgnoreCase(filterExistingAsiDTO.getTypeOfExam())))
                    .collect(Collectors.toCollection(ArrayList::new));
        }*/

        if(!filterExistingAsiDTO.getSortBy().equalsIgnoreCase("")){
            if(filterExistingAsiDTO.getSortBy().equalsIgnoreCase("rating")){
                clinics.sort((Clinic c1, Clinic c2) -> c1.getClinicRatings().stream().collect(Collectors.averagingDouble(cr1 -> cr1.getGrade()))
                        .compareTo(c2.getClinicRatings().stream().collect(Collectors.averagingDouble(cr2 -> cr2.getGrade()))));
            }

        }


        HashMap<Long,Double> ratings = new HashMap<>();
        for(Clinic clinic: clinics){
            double clinicRating = clinic.getClinicRatings().stream().collect(Collectors.averagingDouble(cr -> cr.getGrade()));
            ratings.put(clinic.getId(),clinicRating);
        }

        if(!filterExistingAsiDTO.getRating().equalsIgnoreCase("")){
            double ratingDTO = Double.parseDouble(filterExistingAsiDTO.getRating());
            clinics = clinics.stream().filter(clinic -> ratings.get(clinic.getId()) == ratingDTO).collect(Collectors.toCollection(ArrayList::new));
        }


        /*advancedSearchItems = clinics.stream()
                .map(clinic -> new AdvancedSearchItem(clinic.getId().toString(), clinic.getName(), String.valueOf(ratings.get(clinic.getId())), clinic.getAddress(), String.valueOf(te.getPriceItem().getPrice())))
                .collect(Collectors.toCollection(ArrayList::new));
*/
        advancedSearchItems = clinics.stream()
                .map(clinic -> new AdvancedSearchItem(clinic.getId().toString(), clinic.getName(),ratings.get(clinic.getId()) == 0 ? "No rating" : String.valueOf(ratings.get(clinic.getId())), clinic.getAddress(), String.valueOf(te.getPriceItem().getPrice())))
                .collect(Collectors.toCollection(ArrayList::new));

        return advancedSearchItems;
    }


    public MessageDTO checkClinicHasFreeDoctorsForSpecificDateAndTypeOfExam(long clinicId ,AdvancedSearchClinicDTO advancedSearchClinicDTO) {
        Clinic clinic = clinicRepository.findById(clinicId);
        LocalDate localDate = LocalDate.parse(advancedSearchClinicDTO.getDate());

        TypeOfExam typeOfExam = typeOfExamRepository.findByName(advancedSearchClinicDTO.getTypeOfExam());

        boolean typeOfExamExists = clinic.getTypeOfExams()
                .stream().anyMatch(te->te.getName().equalsIgnoreCase(advancedSearchClinicDTO.getTypeOfExam()));

        if(!typeOfExamExists){
            return new MessageDTO("There is no doctors for this type of exam!",false);
        }

        List<Doctor> doctors = clinic.getStaff();

        boolean hasFreeAtLeastOneDoctorAtCertainDay = false;
        if(doctors.size() > 0){
            hasFreeAtLeastOneDoctorAtCertainDay = this.hasFreeAtLeastOneDoctorAtCertainDay(localDate,doctors,typeOfExam);
        }

        if(!hasFreeAtLeastOneDoctorAtCertainDay) {
            return new MessageDTO("There is not free doctors for date that you entered!", false);
        }
        return new MessageDTO("There is at least one doctor free :)", true);
    }

    public boolean hasFreeAtLeastOneDoctorAtCertainDay(LocalDate localDate, List<Doctor> doctors, TypeOfExam typeOfExam){
        for (Doctor doctor : doctors) {
            LocalDate today = LocalDate.now();
            List<LeaveForm> vacations = doctor.getVacations()
                    .stream().filter(leaveForm -> leaveForm.getStartDate().isAfter(today))
                    .collect(Collectors.toCollection(ArrayList::new));


            boolean doctorHasFreeTerms = false;
            boolean isBetweenOrEqual = false;
            if (vacations.size() > 0) {
                for (LeaveForm vacation : vacations) {
                    LocalDate vacationStartDate = vacation.getStartDate();
                    LocalDate vacationEndDate = vacation.getEndDate();

                    isBetweenOrEqual = this.dateIsBetweenOrEqual(localDate, vacationStartDate, vacationEndDate);

                    if (!isBetweenOrEqual) {
                        break;
                    }

                }
            }

            if(!isBetweenOrEqual){
                doctorHasFreeTerms = doctorService.findFreeDoctorSlotsForSpecificDate(doctor,localDate,typeOfExam).size() > 0;
            }

            if(doctorHasFreeTerms){
                return true;
            }


        }
        return false;
    }


    public boolean dateIsBetweenOrEqual(LocalDate date, LocalDate startDate, LocalDate endDate){
        if((date.isAfter(startDate) && date.isBefore(endDate)) || date.equals(startDate) || date.equals(endDate)){
            return true;
        }
        return false;
    }
}
