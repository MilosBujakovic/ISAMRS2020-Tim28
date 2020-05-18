package com.ServisKlinickihCentara.service;


import com.ServisKlinickihCentara.dto.doctorDTO.DoctorFreeSlotsViewDTO;
import com.ServisKlinickihCentara.dto.doctorDTO.ShiftDTO;
import com.ServisKlinickihCentara.dto.doctorDTO.TimeShift;
import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.employees.Doctor;
import com.ServisKlinickihCentara.model.employees.DoctorRating;
import com.ServisKlinickihCentara.model.employees.LeaveForm;
import com.ServisKlinickihCentara.model.patients.Appointment;
import com.ServisKlinickihCentara.repository.ClinicRepository;
import com.ServisKlinickihCentara.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

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
    private ClinicService clinicService;


    public ArrayList<DoctorFreeSlotsViewDTO> getForClinicDoctorsFreeSlots(String clinic_id, String date){
        ArrayList<DoctorFreeSlotsViewDTO> doctorFreeSlotsViewDTOS = new ArrayList<>();

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
                ArrayList<ShiftDTO> freeSlots = this.findFreeDoctorSlotsForSpecificDate(doctor,dateDTO);

                double rating = doctor.getRatings().stream().collect(Collectors.averagingDouble(dr -> dr.getGrade()));

                String ratingString = String.valueOf(rating);
                if(rating == 0){
                    ratingString = "No rating!";
                }

                DoctorFreeSlotsViewDTO d = new DoctorFreeSlotsViewDTO(doctor.getName(),doctor.getSurname(),ratingString,freeSlots);
                doctorFreeSlotsViewDTOS.add(d);

            }
        }

        return doctorFreeSlotsViewDTOS;
    }

    public ArrayList<ShiftDTO> findFreeDoctorSlotsForSpecificDate(Doctor doctor, LocalDate localDate){
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
            boolean moreOrEqualThan15Minutes = convertToMinutes(takenSlots.get(0).getStartTime()) - convertToMinutes(doctorShiftStart) >= 15;

            if(moreOrEqualThan15Minutes){
                freeSlots.add(new ShiftDTO(doctorShiftStart.toString(),takenSlots.get(0).getStartTime().toString()));
            }

            int n = takenSlots.size();
            for(int i = 1; i < n; i++){
                moreOrEqualThan15Minutes = convertToMinutes(takenSlots.get(i).getStartTime()) - convertToMinutes(takenSlots.get(i - 1).getEndTime()) >= 15;
                if(moreOrEqualThan15Minutes){
                    freeSlots.add(new ShiftDTO(takenSlots.get(i - 1).getEndTime().toString(),takenSlots.get(i).getStartTime().toString()));
                }
            }

            moreOrEqualThan15Minutes = convertToMinutes(doctorShiftEnd) - convertToMinutes(takenSlots.get(n - 1).getEndTime()) >= 15;

            if(moreOrEqualThan15Minutes){
                freeSlots.add(new ShiftDTO(takenSlots.get(n - 1).getEndTime().toString(),doctorShiftEnd.toString()));
            }

        }
        return freeSlots;
    }


    public int convertToMinutes(Time time){
        return time.getHours() * 60 + time.getMinutes();
    }

}
