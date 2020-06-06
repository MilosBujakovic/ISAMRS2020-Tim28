package com.ServisKlinickihCentara.service;


import com.ServisKlinickihCentara.dto.doctorDTO.*;
import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.clinics.TypeOfExam;
import com.ServisKlinickihCentara.model.employees.Doctor;
import com.ServisKlinickihCentara.model.employees.DoctorRating;
import com.ServisKlinickihCentara.model.employees.LeaveForm;
import com.ServisKlinickihCentara.model.patients.Appointment;
import com.ServisKlinickihCentara.repository.ClinicRepository;
import com.ServisKlinickihCentara.repository.DoctorRepository;
import com.ServisKlinickihCentara.repository.TypeOfExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private TypeOfExamRepository typeOfExamRepository;

    @Autowired
    private ClinicService clinicService;


    public ArrayList<DoctorFreeSlotsViewDTO> getForClinicDoctorsFreeSlots(String clinic_id, String date, String te){
        ArrayList<DoctorFreeSlotsViewDTO> doctorFreeSlotsViewDTOS = new ArrayList<>();
        TypeOfExam typeOfExam = typeOfExamRepository.findByName(te);

        Clinic clinic = clinicRepository.findById(Long.parseLong(clinic_id));

        List<Doctor> doctors = clinic.getStaff();

        LocalDate dateDTO = LocalDate.parse(date);

        for(Doctor doctor: doctors) {
            LocalDate today = LocalDate.now();
            List<LeaveForm> vacations = doctor.getVacations()
                    .stream().filter(leaveForm -> leaveForm.getStartDate().isAfter(today))
                    .collect(Collectors.toCollection(ArrayList::new));
            boolean doctorIsFree = true;
            for(LeaveForm leaveForm: vacations){
                if(clinicService.dateIsBetweenOrEqual(dateDTO,leaveForm.getStartDate(),leaveForm.getEndDate())){
                    doctorIsFree = false;
                    break;
                }
            }

            if(doctorIsFree){
                ArrayList<ShiftDTO> freeSlots = this.findFreeDoctorSlotsForSpecificDate(doctor, dateDTO, typeOfExam);

                double rating = doctor.getRatings().stream().collect(Collectors.averagingDouble(dr -> dr.getGrade()));

                String ratingString = String.valueOf(rating);
                if(rating == 0){
                    ratingString = "No rating!";
                }

                DoctorFreeSlotsViewDTO d = new DoctorFreeSlotsViewDTO(doctor.getId().toString(),doctor.getName(),doctor.getSurname(),ratingString,freeSlots);
                doctorFreeSlotsViewDTOS.add(d);

            }
        }

        return doctorFreeSlotsViewDTOS;
    }

    public ArrayList<ShiftDTO> findFreeDoctorSlotsForSpecificDate(Doctor doctor, LocalDate localDate, TypeOfExam typeOfExam){
        ArrayList<ShiftDTO> freeSlots = new ArrayList<>();
        List<Appointment> appointments = doctor.getWorkingCalendar();
        Time doctorShiftStart = doctor.getShiftStart();
        Time doctorShiftEnd = doctor.getShiftEnd();

        ArrayList<TimeShift> takenSlots = appointments.stream()
                .filter(a->a.getTerm().getStartTime().toLocalDateTime().toLocalDate().equals(localDate))
                .map(a -> new TimeShift(Time.valueOf(a.getTerm().getStartTime().toLocalDateTime().toLocalTime()),
                        Time.valueOf(a.getTerm().getEndTime().toLocalDateTime().toLocalTime())))
                .collect(Collectors.toCollection(ArrayList::new));


        if(takenSlots.size() == 0){
            ShiftDTO shiftDTO = new ShiftDTO(doctorShiftStart.toString(),doctorShiftEnd.toString());
            freeSlots.add(shiftDTO);
        }else{
            int duration = typeOfExam.getDuration();

            boolean moreOrEqualThan = convertToMinutes(takenSlots.get(0).getStartTime()) - convertToMinutes(doctorShiftStart) >= duration;

            if(moreOrEqualThan){
                freeSlots.add(new ShiftDTO(doctorShiftStart.toString(),takenSlots.get(0).getStartTime().toString()));
            }

            int n = takenSlots.size();
            for(int i = 1; i < n; i++){
                moreOrEqualThan = convertToMinutes(takenSlots.get(i).getStartTime()) - convertToMinutes(takenSlots.get(i - 1).getEndTime()) >= duration;
                if(moreOrEqualThan){
                    freeSlots.add(new ShiftDTO(takenSlots.get(i - 1).getEndTime().toString(),takenSlots.get(i).getStartTime().toString()));
                }
            }

            moreOrEqualThan = convertToMinutes(doctorShiftEnd) - convertToMinutes(takenSlots.get(n - 1).getEndTime()) >= duration;

            if(moreOrEqualThan){
                freeSlots.add(new ShiftDTO(takenSlots.get(n - 1).getEndTime().toString(),doctorShiftEnd.toString()));
            }

        }
        return freeSlots;
    }


    public ArrayList<DoctorFreeSlotsViewDTO> filterExistingDoctors(List<String> ids, DoctorSearchDTO doctorSearchDTO, String te){
        ArrayList<DoctorFreeSlotsViewDTO> doctorFreeSlotsViewDTOS = new ArrayList<>();
        ArrayList<Doctor> doctors = doctorRepository.findAll();
        TypeOfExam typeOfExam = typeOfExamRepository.findByName(te);

        doctors = doctors.stream().filter(d -> ids.contains(d.getId().toString()))
                .filter(d->d.getName().equalsIgnoreCase("") || d.getName().toLowerCase().contains(doctorSearchDTO.getName().toLowerCase()))
                .filter(d->d.getSurname().equalsIgnoreCase("") || d.getSurname().toLowerCase().contains(doctorSearchDTO.getSurname().toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));


        LocalDate specificDate = LocalDate.parse(doctorSearchDTO.getDate());

        for(Doctor doctor: doctors){

            List<DoctorRating> doctorRatings = doctor.getRatings();
            double rating = doctorRatings.stream().collect(Collectors.averagingDouble(dr->dr.getGrade()));

            if(!doctorSearchDTO.getRating().equalsIgnoreCase("")){

                if(rating != Double.parseDouble(doctorSearchDTO.getRating())){
                    continue;
                }
            }

            String ratingDTO = "No rating!";

            if(rating != 0){
                ratingDTO = String.valueOf(rating);
            }

            ArrayList<ShiftDTO> shiftDTOS = this.findFreeDoctorSlotsForSpecificDate(doctor,specificDate,typeOfExam);
            doctorFreeSlotsViewDTOS.add(new DoctorFreeSlotsViewDTO(doctor.getId().toString(), doctor.getName(),doctor.getSurname(),ratingDTO,shiftDTOS));
        }

        return doctorFreeSlotsViewDTOS;
    }

    public ClinicDoctorNameDTO getClinicAndDoctor(String clinicId,String doctorId){
        Clinic clinic = clinicRepository.findById(Long.parseLong(clinicId));
        Doctor doctor = doctorRepository.findById(Long.parseLong(doctorId));

        ClinicDoctorNameDTO clinicDoctorNameDTO = new ClinicDoctorNameDTO(clinic.getName(),doctor.getName() + " " + doctor.getSurname());
        return clinicDoctorNameDTO;
    }

    public int convertToMinutes(Time time){
        return time.getHours() * 60 + time.getMinutes();
    }

}
