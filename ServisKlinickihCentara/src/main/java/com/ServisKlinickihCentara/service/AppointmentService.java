package com.ServisKlinickihCentara.service;


import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.appointmentsDTO.*;
import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.clinics.ClinicRating;
import com.ServisKlinickihCentara.model.clinics.Term;
import com.ServisKlinickihCentara.model.clinics.TypeOfExam;
import com.ServisKlinickihCentara.model.employees.Doctor;
import com.ServisKlinickihCentara.model.employees.DoctorRating;
import com.ServisKlinickihCentara.model.enums.AppointmentType;
import com.ServisKlinickihCentara.model.enums.RequestStatus;
import com.ServisKlinickihCentara.model.enums.Specialty;
import com.ServisKlinickihCentara.model.patients.Appointment;
import com.ServisKlinickihCentara.model.patients.AppointmentRequest;
import com.ServisKlinickihCentara.model.patients.Patient;
import com.ServisKlinickihCentara.model.users.ClinicAdmin;
import com.ServisKlinickihCentara.repository.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
    private ClinicRatingRepository clinicRatingRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorRatingRepository doctorRatingRepository;

    @Autowired
    private TypeOfExamRepository typeOfExamRepository;

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
            String type_of_exam = appointment.getTypeOfExam().getName();

            double price = appointment.getTypeOfExam().getPriceItem().getPrice();
            double discount = appointment.getTypeOfExam().getPriceItem().getDiscount();

            PredefinedAppointmenViewtDTO p = new PredefinedAppointmenViewtDTO(appointment.getId().toString(),
                    dateTime,term.getRoom().getNumber(),doctorNameSurname,type_of_exam,String.valueOf(price), String.valueOf((int)(discount*100) + "%"));

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
        TypeOfExam type_of_exam = appointment.getTypeOfExam();
        AppointmentType appointmentType = appointment.getType();

        AppointmentRequest appointmentRequest = new AppointmentRequest(patient,term,
                doctor,type_of_exam,appointmentType,true);
        appointmentRequestRepository.save(appointmentRequest);


        String messageBody = "Click below to accept appointment reservation: \n 'http://localhost:8080/appointment/acceptQuickAppointment/" +
                patient.getUuid() + "/" + appointment.getId() + "/" + appointmentRequest.getId() +
                "\n or Click below to decline appointment reservation \n" +
                "http://localhost:8080/appointment/declineQuickAppointment/" +
                patient.getUuid() + "/" + appointmentRequest.getId();

        //koristicemo samo jedan email za svakog pacijenta radi lakseg testiranja
        emailService.sendMail("slavengaric@gmail.com",
                "Confirmation of reservation appointment",messageBody);

        return new MessageDTO("Your request for reservation was approved, go to your email to confirm reservation.",true);
    }


    public String acceptQuickAppointment(String uuid, String appointmentId, String appointmentRequestId){
        Patient patient = (Patient) userService.findByUuid(uuid);

        if(patient == null){
            return "Something is wrong, patient doesn't exist!!!";
        }

        Appointment appointment = appointmentRepository.findById(Long.parseLong(appointmentId));

        if(appointment == null){
            return new String("Something is wrong, appointment with this id doesn't exist!!!");
        }

        AppointmentRequest  appointmentRequest = appointmentRequestRepository.findById(Long.parseLong(appointmentRequestId));

        if(appointmentRequest == null){
            return "Something is wrong, appointment request with this id doesn't exist!!!";
        }


        Timestamp today = new Timestamp(System.currentTimeMillis());

        Term term = appointment.getTerm();
        Timestamp startTime = term.getStartTime();

        if(today.after(startTime)){
            return "Date for this appointment was passed!!!";
        }



        Patient p = appointment.getPatient();
        if (p != null){
            return "Someone reserved this appointment before you!!!";
        }

        appointment.setPatient(patient);
        appointmentRequest.setStatus(RequestStatus.ACCEPTED);
        appointmentRepository.save(appointment);
        appointmentRequestRepository.save(appointmentRequest);
        String link = "<a href='http://localhost:8080/redirect.html' target='_blank'>http://localhost:8080/redirect.html</a>";
        return "You reserved successfully appointment, click below to go on your reserved appointments \n" + link;
    }


    public String declineQuickAppointment(String uuid, String appointmentRequestId){
        Patient patient = (Patient) userService.findByUuid(uuid);
        AppointmentRequest appointmentRequest = appointmentRequestRepository.findById(Long.parseLong(appointmentRequestId));

        if(appointmentRequest == null){
            return "Something is wrong, appointment request with this id doesn't exist!!!";
        }


        appointmentRequest.setStatus(RequestStatus.DECLINED);
        appointmentRequestRepository.save(appointmentRequest);
        return  "You declined successfully appointment!!!";
    }

    public ArrayList<ReservedAppointmentDTO> getPatientsAppointments(String email){
        Patient patient = (Patient) userService.findByUsername(email);
        ArrayList<ReservedAppointmentDTO> reservedAppointmentDTOS = new ArrayList<>();

        List<Appointment> appointments = patient.getApointments();
        List<AppointmentRequest> appointmentRequests = patient.getRequests();


        for(Appointment a: appointments){
            System.out.println(a.getTerm().getStartTime());
            Timestamp today = new Timestamp(System.currentTimeMillis());
            if(today.after(a.getTerm().getStartTime())){
                continue;
            }
            double price = a.getTypeOfExam().getPriceItem().getPrice();

            ReservedAppointmentDTO reservedAppointmentDTO = new ReservedAppointmentDTO(a.getId().toString(),
                    a.getTerm().getStartTime().toString(),a.getTerm().getRoom().getNumber(),
                    a.getEmployee().getName() + " " + a.getEmployee().getSurname(), a.getTypeOfExam().getName(),
                    String.valueOf(price),RequestStatus.ACCEPTED.toString());
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
            double price = ar.getTypeOfExam().getPriceItem().getPrice();

            Appointment a = this.findAppointmentByDoctorAndStartTime(ar.getDoctor().getId(),ar.getTerm().getStartTime());
            ReservedAppointmentDTO reservedAppointmentDTO = new ReservedAppointmentDTO(a.getId().toString(),
                    ar.getTerm().getStartTime().toString(),ar.getTerm().getRoom().getNumber(),
                    ar.getDoctor().getName() + " " + ar.getDoctor().getSurname(), ar.getTypeOfExam().getName(),
                    String.valueOf(price),ar.getStatus().toString());
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

    public MessageDTO customAppointmentReservation(CustomAppointmentDTO customAppointmentDTO){
        Patient patient = (Patient) userService.findByUsername(customAppointmentDTO.getEmail());
        Clinic clinic = clinicRepository.findById(Long.parseLong(customAppointmentDTO.getClinicId()));
        Doctor doctor = doctorRepository.findById(Long.parseLong(customAppointmentDTO.getDoctorId()));
        TypeOfExam typeOfExam = typeOfExamRepository.findByName(customAppointmentDTO.getTypeOfExam());

        Time startTime = Time.valueOf(customAppointmentDTO.getStartTime());
        LocalDate date = LocalDate.parse(customAppointmentDTO.getDate());
        LocalDateTime localDateTime = date.atTime(startTime.getHours(),startTime.getMinutes());

        int duration = typeOfExam.getDuration();

        Timestamp start = Timestamp.valueOf(localDateTime);
        Timestamp end = new Timestamp(start.getTime());
        end.setTime(end.getTime() + TimeUnit.MINUTES.toMillis(duration));

        Term term = new Term(start,end);

        AppointmentRequest appointmentRequest = new AppointmentRequest(patient,term,doctor,typeOfExam,AppointmentType.CHECKUP,false);
        appointmentRequest.setClinic(clinic);
        appointmentRequestRepository.save(appointmentRequest);

        ClinicAdmin clinicAdmin = clinic.getAdmin();

        System.out.println(clinicAdmin.getEmail());

        emailService.sendMail("milosslaven96@gmail.com",
                "Request for appointment reservation of ","Patient "
                        + patient.getEmail() +  " wants to reserve "
                        + typeOfExam.getName() +  " checkup appointment at the doctor " + doctor.getName() + " "
                        + doctor.getSurname() + " at " + start.toString() + "."
                );

        return new MessageDTO("You successfully sent request for reservation of appointment",true);
    }




    public ArrayList<HistoryVisitDTO> getPatientsHistory(String email){
        ArrayList<HistoryVisitDTO> historyVisitDTOS = new ArrayList<>();
        long patient_id = userService.findByUsername(email).getId();

        LocalDateTime today = LocalDateTime.now();
        /*historyVisitDTOS = this.getPatientsAppointmentsFromPast(email).stream().map(a -> new HistoryVisitDTO(a.getTerm().getStartTime().toLocalDateTime().toLocalDate().toString(),
                a.getClinic().getName(),a.getEmployee().getName() + ' ' + a.getEmployee().getSurname(),
                a.getType().toString(),a.getCategory().toString(),String.valueOf(a.getTerm().getPrice()))).collect(Collectors.toCollection(ArrayList::new));
*/
        ArrayList<Appointment> appointments = this.getPatientsAppointmentsFromPast(email);
        this.mapAppointmentsHistoryWithGrades(appointments,historyVisitDTOS,patient_id);
        return historyVisitDTOS;
    }


    public ArrayList<HistoryVisitDTO> filterSortingPatientsHistory(HistoryVisitFilterSortDTO hv){
        ArrayList<HistoryVisitDTO> filteredSortedvisits = new ArrayList<>();
        ArrayList<Appointment> appointments = this.getPatientsAppointmentsFromPast(hv.getEmail());
        long patient_id = userService.findByUsername(hv.getEmail()).getId();


        if(!hv.getSortingType().equalsIgnoreCase("")){
            if(hv.getSortingType().equalsIgnoreCase("date")){
                appointments.sort((Appointment a1, Appointment a2)->a1.getTerm().getStartTime().compareTo(a2.getTerm().getStartTime()));
            }else if(hv.getSortingType().equalsIgnoreCase("typeOfExam")){
                appointments.sort((Appointment a1,Appointment a2)-> a1.getTypeOfExam().getName().compareTo(a2.getTypeOfExam().getName()));
            }else if(hv.getSortingType().equalsIgnoreCase("price")){
                appointments.sort((Appointment a1, Appointment a2)-> Double.compare(a1.getTypeOfExam().getPriceItem().getPrice(),a2.getTypeOfExam().getPriceItem().getPrice()));
            }
        }

        appointments = appointments.stream()
                .filter(a->hv.getTypeOfExam().equalsIgnoreCase("") || a.getTypeOfExam().getName().equalsIgnoreCase(hv.getTypeOfExam()))
                .filter(a->hv.getVisitType().equalsIgnoreCase("") || a.getType().toString().equalsIgnoreCase(hv.getVisitType()))
                .collect(Collectors.toCollection(ArrayList::new));
                /*.map(a->new HistoryVisitDTO(a.getTerm().getStartTime().toLocalDateTime().toLocalDate().toString(),
                        a.getClinic().getName(),a.getEmployee().getName() + " " + a.getEmployee().getSurname(),
                        a.getType().toString(),a.getCategory().toString(),String.valueOf(a.getTerm().getPrice()))).collect(Collectors.toCollection(ArrayList::new));
*/
        this.mapAppointmentsHistoryWithGrades(appointments,filteredSortedvisits,patient_id);
        return filteredSortedvisits;
    }

    public void mapAppointmentsHistoryWithGrades(ArrayList<Appointment> appointments, ArrayList<HistoryVisitDTO> appointmentsHistory, long patient_id){
        for(Appointment a: appointments){
            long clinic_id = a.getClinic().getId();
            String clinicRatingDTO = "No rating!";
            ClinicRating clinicRating = clinicRatingRepository.findByClinicIdAndPatientId(clinic_id,patient_id);
            if(clinicRating != null){
                clinicRatingDTO = String.valueOf(clinicRating.getGrade());
            }
            long doctor_id = a.getEmployee().getId();
            String doctorRatingDTO = "No rating!";
            DoctorRating doctorRating = doctorRatingRepository.findByDoctorIdAndPatientId(doctor_id,patient_id);
            if(doctorRating != null){
                doctorRatingDTO = String.valueOf(doctorRating.getGrade());
            }
            System.out.println(a);
            System.out.println(String.valueOf(a.getTypeOfExam().getPriceItem().getPrice()));

            appointmentsHistory.add(new HistoryVisitDTO(a.getTerm().getStartTime().toLocalDateTime().toLocalDate().toString(),
                    a.getClinic().getName(),a.getEmployee().getName() + " " + a.getEmployee().getSurname(),
                    a.getType().toString(), a.getTypeOfExam().getName(), String.valueOf(a.getTypeOfExam().getPriceItem().getPrice()),clinicRatingDTO,doctorRatingDTO, String.valueOf(doctor_id)));
        }
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
