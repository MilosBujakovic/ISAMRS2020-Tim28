package com.ServisKlinickihCentara.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ServisKlinickihCentara.dto.appointmentsDTO.AppointmentTermDTO;
import com.ServisKlinickihCentara.dto.appointmentsDTO.MakePredefinedAppointmentDTO;
import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.clinics.Room;
import com.ServisKlinickihCentara.model.clinics.Term;
import com.ServisKlinickihCentara.model.clinics.TypeOfExam;
import com.ServisKlinickihCentara.model.employees.Doctor;
import com.ServisKlinickihCentara.model.enums.AppointmentType;
import com.ServisKlinickihCentara.model.patients.Appointment;
import com.ServisKlinickihCentara.repository.AppointmentRepository;
import com.ServisKlinickihCentara.repository.ClinicRepository;
import com.ServisKlinickihCentara.repository.DoctorRepository;
import com.ServisKlinickihCentara.repository.RoomRepository;
import com.ServisKlinickihCentara.repository.TermRepository;
import com.ServisKlinickihCentara.repository.TypeOfExamRepository;

@Service
public class PredefinedAppointmentService 
{
	@Autowired
	TypeOfExamService examsService;
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	ClinicRepository clinicRepository;
	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	TypeOfExamRepository typeOfExamRepository;
	@Autowired
	TermRepository termRepository;
	
	
	public List<AppointmentTermDTO> getTerms(AppointmentTermDTO appointmentSlot)
	{
		List<AppointmentTermDTO> terms = new ArrayList<AppointmentTermDTO>();
		Time startTime = Time.valueOf(appointmentSlot.getStartTime() );
		Time finalTime = Time.valueOf(appointmentSlot.getEndTime() );
		int i = 0;
		AppointmentTermDTO term;
		TypeOfExam te = examsService.getExamType(appointmentSlot.getTypeOfExam());
		do
		{
			term = new AppointmentTermDTO();
			term.setStartTime(startTime.toString());
			startTime.setMinutes(startTime.getMinutes()+te.getDuration());
			term.setEndTime(startTime.toString());
			term.setTypeOfExam(appointmentSlot.getTypeOfExam());
			terms.add(term);
			//System.out.println(terms.get(i).getStartTime() + "|" + terms.get(i).getEndTime() + "|" + terms.get(i).getTypeOfExam());
			//i++;
			
		}
		while(startTime.before(finalTime));
		
		return terms;
	}

	public boolean makeAppointment(MakePredefinedAppointmentDTO predefinedAppointment) {
		Appointment appointment = new Appointment();
		//TERM:
		Timestamp startTime = createTimestamp(predefinedAppointment.getDateOfCheckup() , predefinedAppointment.getStartTime());
		Timestamp endTime = createTimestamp(predefinedAppointment.getDateOfCheckup() , predefinedAppointment.getEndTime());
		Room room = roomRepository.findById(predefinedAppointment.getRoomId() );
		Term term = new Term(room, startTime, endTime);
		termRepository.save(term);//TODO: U Term ne upisuje RoomId
		
		
		Clinic clinic = clinicRepository.findById(predefinedAppointment.getClinicId() );
		Doctor doctor = doctorRepository.findById(predefinedAppointment.getDoctorId() );
		
		TypeOfExam typeOfExam = typeOfExamRepository.findByName(predefinedAppointment.getTypeOfExam() );
		
		appointment.setTerm(term);
		appointment.setClinic(clinic);
		appointment.setEmployee(doctor);
		appointment.setType(AppointmentType.CHECKUP);//ClinicAdmin can create only predefined checkup appointments!
		appointment.setTypeOfExam(typeOfExam);
		appointment.setPredefined(true);//this is a predefined appointment
		appointment.setActive(true);//Can be taken by a pacient
		appointment.setCancelled(false);//Patient hasn't cancelled yet
		
		try
		{	
			appointmentRepository.save(appointment);
			doctor.getWorkingCalendar().add(appointment);
			doctorRepository.save(doctor);
			clinic.getFreeTerms();//TODO: da li i ovdje nesto treba?
			System.out.println("Predefined appointment Saved!");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Saving Predefined appointment has encountered an error!");
			return false;
		}
	}
	
	
	public Timestamp createTimestamp(String date, String time)
	{
		//LocalDate.parse(date);
		//Time.valueOf(time);
		Timestamp t = Timestamp.valueOf(date+" "+time);
		System.out.println("Timestamp is: " + t);
		return t;
	}
	
}
