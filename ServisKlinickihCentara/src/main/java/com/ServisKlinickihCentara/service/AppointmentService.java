package com.ServisKlinickihCentara.service;


import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.appointmentsDTO.HistoryVisitDTO;
import com.ServisKlinickihCentara.dto.appointmentsDTO.HistoryVisitFilterSortDTO;
import com.ServisKlinickihCentara.dto.appointmentsDTO.PredefinedAppointmenViewtDTO;
import com.ServisKlinickihCentara.dto.appointmentsDTO.ReservedAppointmentDTO;
import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.clinics.Term;
import com.ServisKlinickihCentara.model.employees.Doctor;
import com.ServisKlinickihCentara.model.enums.AppointmentType;
import com.ServisKlinickihCentara.model.enums.RequestStatus;
import com.ServisKlinickihCentara.model.enums.Specialty;
import com.ServisKlinickihCentara.model.patients.Appointment;
import com.ServisKlinickihCentara.model.patients.AppointmentRequest;
import com.ServisKlinickihCentara.model.patients.Patient;
import com.ServisKlinickihCentara.repository.AppointmentRepository;
import com.ServisKlinickihCentara.repository.AppointmentRequestRepository;
import com.ServisKlinickihCentara.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentRequestRepository appointmentRequestRepository;


    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

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

    public MessageDTO quickAppointmentReservation(String email,String appointmentId){
        Patient patient = (Patient) userService.findByUsername(email);

        if(patient == null){
            return new MessageDTO("Patient with this email doesn't exist!!!", false);
        }


        Appointment appointment = appointmentRepository.findById(Long.parseLong(appointmentId));

        if(appointment == null){
            return new MessageDTO("Appointment with this id doesn't exist!!!", false);
        }


        if(!appointment.isActive()){
            return new MessageDTO("Appointment is not active!!!", false);
        }


        if(appointment.getPatient() != null){
            return new MessageDTO("Other patient reserve this appointment !!!", false);
        }


        Timestamp today = new Timestamp(System.currentTimeMillis());

        Term term = appointment.getTerm();
        Timestamp startTime = term.getStartTime();

        if(today.after(startTime)){
            return new MessageDTO("Date for this appointment was passed!!!", false);
        }

        Doctor doctor = (Doctor) appointment.getEmployee();
        Specialty category = appointment.getCategory();
        AppointmentType appointmentType = appointment.getType();

        AppointmentRequest appointmentRequest = new AppointmentRequest(patient,term,
                doctor,category,appointmentType);
        appointmentRequestRepository.save(appointmentRequest);


        String messageBody = "Click below to accept appointment reservation: \n 'http://localhost:8080/appointment/acceptQuickAppointment/" +
                patient.getUuid() + "/" + appointment.getId() + "/" + appointmentRequest.getId() +
                "\n or Click below to decline appointment reservation \n" +
                "http://localhost:8080/appointment/declineQuickAppointment/" +
                patient.getUuid() + "/" + appointmentRequest.getId();

        //koristicemo samo jedan email za svakog pacijenta
        emailService.sendMail("slavengaric@gmail.com",
                "Confirmation of reservation appointment",messageBody);

        return new MessageDTO("Your request for reservation was approved, go to your email to confirm reservation.",true);
    }


    public MessageDTO acceptQuickAppointment(String uuid, String appointmentId, String appointmentRequestId){
        Patient patient = (Patient) userService.findByUuid(uuid);

        if(patient == null){
            return new MessageDTO("Something is wrong, patient doesn't exist!!!",false);
        }

        Appointment appointment = appointmentRepository.findById(Long.parseLong(appointmentId));

        if(appointment == null){
            return new MessageDTO("Something is wrong, appointment with this id doesn't exist!!!",false);
        }

        AppointmentRequest  appointmentRequest = appointmentRequestRepository.findById(Long.parseLong(appointmentRequestId));

        if(appointmentRequest == null){
            return new MessageDTO("Something is wrong, appointment request with this id doesn't exist!!!",false);
        }


        Timestamp today = new Timestamp(System.currentTimeMillis());

        Term term = appointment.getTerm();
        Timestamp startTime = term.getStartTime();

        if(today.after(startTime)){
            return new MessageDTO("Date for this appointment was passed!!!", false);
        }



        Patient p = appointment.getPatient();
        if (p != null){
            return new MessageDTO("Someone reserved this appointment before you!!!", false);
        }

        appointment.setPatient(patient);
        appointmentRequest.setStatus(RequestStatus.ACCEPTED);
        appointmentRepository.save(appointment);
        appointmentRequestRepository.save(appointmentRequest);
        return new MessageDTO("You reserved successfully appointment!!!", true);
    }


    public MessageDTO declineQuickAppointment(String uuid, String appointmentRequestId){
        Patient patient = (Patient) userService.findByUuid(uuid);
        AppointmentRequest appointmentRequest = appointmentRequestRepository.findById(Long.parseLong(appointmentRequestId));

        if(appointmentRequest == null){
            return new MessageDTO("Something is wrong, appointment request with this id doesn't exist!!!",false);
        }


        appointmentRequest.setStatus(RequestStatus.DECLINED);
        appointmentRequestRepository.save(appointmentRequest);
        return new MessageDTO("You declined successfully appointment!!!", true);
    }

    public ArrayList<ReservedAppointmentDTO> getPatientsAppointments(String email){
        Patient patient = (Patient) userService.findByUsername(email);
        ArrayList<ReservedAppointmentDTO> reservedAppointmentDTOS = new ArrayList<>();

        List<Appointment> appointments = patient.getApointments();
        List<AppointmentRequest> appointmentRequests = patient.getRequests();

        System.out.println(appointments.size());

        for(Appointment a: appointments){
            System.out.println(a.getTerm().getStartTime());
            Timestamp today = new Timestamp(System.currentTimeMillis());
            if(today.after(a.getTerm().getStartTime())){
                continue;
            }
            ReservedAppointmentDTO reservedAppointmentDTO = new ReservedAppointmentDTO(a.getId().toString(),
                    a.getTerm().getStartTime().toString(),a.getTerm().getRoom().getNumber(),
                    a.getEmployee().getName() + " " + a.getEmployee().getSurname(), a.getCategory().toString(),
                    String.valueOf(a.getTerm().getPrice()),RequestStatus.ACCEPTED.toString());
            reservedAppointmentDTOS.add(reservedAppointmentDTO);

        }

        for(AppointmentRequest ar: appointmentRequests){
            if(ar.getStatus() == RequestStatus.ACCEPTED){
                continue;
            }

            Timestamp today = new Timestamp(System.currentTimeMillis());
            if(today.after(ar.getTerm().getStartTime())){
                continue;
            }
            Appointment a = this.findAppointmentByDoctorAndStartTime(ar.getDoctor().getId(),ar.getTerm().getStartTime());
            ReservedAppointmentDTO reservedAppointmentDTO = new ReservedAppointmentDTO(a.getId().toString(),
                    ar.getTerm().getStartTime().toString(),ar.getTerm().getRoom().getNumber(),
                    ar.getDoctor().getName() + " " + ar.getDoctor().getSurname(), ar.getCategory().toString(),
                    String.valueOf(ar.getTerm().getPrice()),ar.getStatus().toString());
            reservedAppointmentDTOS.add(reservedAppointmentDTO);


        }

        return reservedAppointmentDTOS;
    }


    public MessageDTO cancelAppointment(String appointmentId){

        Appointment appointment = appointmentRepository.findById(Long.parseLong(appointmentId));
        Timestamp dayForward = new Timestamp(System.currentTimeMillis() + 24*60*60*1000);

        Timestamp appointmentStartTime = appointment.getTerm().getStartTime();

        if(!dayForward.before(appointmentStartTime)){
            return new MessageDTO("The appointment is less than 24 hours, you cannot cancel them!",false);
        }

        AppointmentRequest appointmentRequest = this.findAppointmentRequestByDoctorAndStartTime(appointment.getEmployee().getId(),
                appointmentStartTime);

        appointmentRequest.setStatus(RequestStatus.DECLINED);
        appointmentRequestRepository.save(appointmentRequest);
        appointment.setCancelled(true);
        //appointment.getPatient().getApointments().removeIf(appointment1 -> appointment.getId() == appointment.getId());
        appointment.setPatient(null);
        appointmentRepository.save(appointment);


        return new MessageDTO("You successfully cancelled appointment!",true);
    }


    public ArrayList<HistoryVisitDTO> getPatientsHistory(String email){
        ArrayList<HistoryVisitDTO> historyVisitDTOS = new ArrayList<>();

        LocalDateTime today = LocalDateTime.now();
        historyVisitDTOS = this.getPatientsAppointmentsFromPast(email).stream().map(a -> new HistoryVisitDTO(a.getTerm().getStartTime().toLocalDateTime().toLocalDate().toString(),
                a.getClinic().getName(),a.getEmployee().getName() + ' ' + a.getEmployee().getSurname(),
                a.getType().toString(),a.getCategory().toString(),String.valueOf(a.getTerm().getPrice()))).collect(Collectors.toCollection(ArrayList::new));

        return historyVisitDTOS;
    }

    public ArrayList<HistoryVisitDTO> filterSortingPatientsHistory(HistoryVisitFilterSortDTO hv){
        ArrayList<HistoryVisitDTO> historyVisitDTOS = this.getPatientsHistory(hv.getEmail());
        ArrayList<HistoryVisitDTO> filteredSortedvisits = new ArrayList<>();
        ArrayList<Appointment> appointments = this.getPatientsAppointmentsFromPast(hv.getEmail());

        if(!hv.getSortingType().equalsIgnoreCase("")){
            if(hv.getSortingType().equalsIgnoreCase("date")){
                appointments.sort((Appointment a1, Appointment a2)->a1.getTerm().getStartTime().compareTo(a2.getTerm().getStartTime()));
            }else if(hv.getSortingType().equalsIgnoreCase("typeSpeciality")){
                appointments.sort((Appointment a1,Appointment a2)-> a1.getCategory().toString().compareTo(a2.getCategory().toString()));
            }else if(hv.getSortingType().equalsIgnoreCase("price")){
                appointments.sort((Appointment a1, Appointment a2)-> Double.compare(a1.getTerm().getPrice(),a2.getTerm().getPrice()));
            }
        }

        filteredSortedvisits = appointments.stream()
                .filter(a->hv.getSpeciality().equalsIgnoreCase("") || a.getCategory().toString().equalsIgnoreCase(hv.getSpeciality()))
                .filter(a->hv.getVisitType().equalsIgnoreCase("") || a.getType().toString().equalsIgnoreCase(hv.getVisitType()))
                .map(a->new HistoryVisitDTO(a.getTerm().getStartTime().toLocalDateTime().toLocalDate().toString(),
                        a.getClinic().getName(),a.getEmployee().getName() + " " + a.getEmployee().getSurname(),
                        a.getType().toString(),a.getCategory().toString(),String.valueOf(a.getTerm().getPrice()))).collect(Collectors.toCollection(ArrayList::new));

        return filteredSortedvisits;
    }


    public ArrayList<Appointment> getPatientsAppointmentsFromPast(String email){
        Patient patient = (Patient) userService.findByUsername(email);
        List<Appointment> appointments = patient.getApointments();
        LocalDateTime today = LocalDateTime.now();
        return appointments.stream().filter(a -> a.getTerm().getStartTime().toLocalDateTime().isBefore(today)).collect(Collectors.toCollection(ArrayList::new));
    }



    public Appointment findAppointmentByDoctorAndStartTime(long doctorId, Timestamp startTime){
        List<Appointment> appointments = appointmentRepository.findAll();

        for(Appointment appointment: appointments){
            if(appointment.getEmployee().getId() == doctorId && appointment.getTerm().getStartTime().equals(startTime)){
                return appointment;
            }
        }
        return null;
    }

    public AppointmentRequest findAppointmentRequestByDoctorAndStartTime(long doctorId, Timestamp startTime){
        List<AppointmentRequest> appointmentRequests = appointmentRequestRepository.findAll();

        for(AppointmentRequest appointmentRequest: appointmentRequests){
            if(appointmentRequest.getDoctor().getId() == doctorId && appointmentRequest.getTerm().getStartTime().equals(startTime)){
                return appointmentRequest;
            }
        }
        return null;
    }





}
