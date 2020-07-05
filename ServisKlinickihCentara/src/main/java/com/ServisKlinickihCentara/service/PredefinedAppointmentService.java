package com.ServisKlinickihCentara.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ServisKlinickihCentara.dto.appointmentsDTO.AppointmentTermDTO;
import com.ServisKlinickihCentara.model.clinics.TypeOfExam;
import com.ServisKlinickihCentara.repository.AppointmentRepository;

@Service
public class PredefinedAppointmentService 
{

	@Autowired
	TypeOfExamService examsService;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
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
	
}
