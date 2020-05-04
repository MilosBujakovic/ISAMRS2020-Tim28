package com.ServisKlinickihCentara.service;


import com.ServisKlinickihCentara.dto.appointmentsDTO.PredefinedAppointmenViewtDTO;
import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.clinics.Term;
import com.ServisKlinickihCentara.model.patients.Appointment;
import com.ServisKlinickihCentara.repository.AppointmentRepository;
import com.ServisKlinickihCentara.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Autowired
    private ClinicRepository clinicRepository;

    public List<PredefinedAppointmenViewtDTO> getPredefinedAppointments(String clinicId){
        Clinic clinic = clinicRepository.findById(Long.parseLong(clinicId));

        List<PredefinedAppointmenViewtDTO> predefinedAppointmenViewtDTOS = new ArrayList<>();

        List<Appointment> appointments = clinic.getAppointments();
        appointments = appointments.stream().filter(appointment -> appointment.isPredefined()
                && appointment.isActive() && appointment.getPatient() == null).collect(Collectors.toCollection(ArrayList::new));

        for(Appointment appointment: appointments){

            //Timestamp dayBefore = new Timestamp(System.currentTimeMillis() - 24*60*60*1000);
            Timestamp today = new Timestamp(System.currentTimeMillis());


            Term term = appointment.getTerm();
            Timestamp startTime = term.getStartTime();

            if (startTime.before(today)){
                continue;
            }

            String doctorNameSurname = appointment.getEmployee().getName() + ' ' + appointment.getEmployee().getSurname();
            String dateTime = startTime.toString();
            String type = appointment.getCategory().toString();

            PredefinedAppointmenViewtDTO p = new PredefinedAppointmenViewtDTO(appointment.getId().toString(),
                    dateTime,term.getRoom().getNumber(),doctorNameSurname,type,String.valueOf(term.getPrice()));

            predefinedAppointmenViewtDTOS.add(p);
        }


        return predefinedAppointmenViewtDTOS;
    }

}
