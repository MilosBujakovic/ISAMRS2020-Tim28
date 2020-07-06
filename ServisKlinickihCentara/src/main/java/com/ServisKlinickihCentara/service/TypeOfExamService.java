package com.ServisKlinickihCentara.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.typeOfExamDTO.TypeOfExamDTO;
import com.ServisKlinickihCentara.model.clinics.PriceItem;
import com.ServisKlinickihCentara.model.clinics.TypeOfExam;
import com.ServisKlinickihCentara.model.enums.AppointmentType;
import com.ServisKlinickihCentara.model.enums.Specialty;
import com.ServisKlinickihCentara.model.patients.Appointment;
import com.ServisKlinickihCentara.model.patients.AppointmentRequest;
import com.ServisKlinickihCentara.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.clinics.TypeOfExam;
import com.ServisKlinickihCentara.repository.TypeOfExamRepository;

@Service
public class TypeOfExamService {

    @Autowired
    private TypeOfExamRepository typeOfExamRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private PriceItemRepository priceItemRepository;

    @Autowired
    private AppointmentRequestRepository appointmentRequestRepository;


    public ArrayList<String> getTypeOfExams(){
        ArrayList<TypeOfExam> typeOfExams = typeOfExamRepository.findAll();
        return typeOfExams.stream().map(typeOfExam -> typeOfExam.getName()).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<String> getTypeOfExamsWithoutOperations(){
        ArrayList<TypeOfExam> typeOfExams = typeOfExamRepository.findAll();
        return typeOfExams.stream()
                .filter(typeOfExam -> !typeOfExam.getName().toLowerCase().contains("operacija"))
                .map(typeOfExam -> typeOfExam.getName()).collect(Collectors.toCollection(ArrayList::new));
    }
    
    public TypeOfExam getExamType(String name)
    {
    	return typeOfExamRepository.findByName(name);
    }

	public ArrayList<String> getTypeOfExamsForClinic(long clinicId) {
		Clinic clinic = clinicRepository.findById(clinicId);
		ArrayList<String> types = new ArrayList<String>();
		for(int i = 0; i < clinic.getTypeOfExams().size(); i++)
		{
			types.add(clinic.getTypeOfExams().get(i).getName() );
		}
		return types;
	}

    public MessageDTO addNewTypeOfExam(TypeOfExamDTO typeOfExamDTO){

        Clinic clinic = clinicRepository.findById(Long.parseLong(typeOfExamDTO.getClinicId()));

        System.out.println(clinic.getTypeOfExams().size());
        boolean alreadyExists = this.getTypeOfExamsForClinic(Long.parseLong(typeOfExamDTO.getClinicId()))
                .stream().filter(te -> te.equalsIgnoreCase(typeOfExamDTO.getName())).count() > 0;

        if(alreadyExists){
            return new MessageDTO("Type of exam with this name already exists!!!", false);
        }

        TypeOfExam te = new TypeOfExam();
        te.setName(typeOfExamDTO.getName());
        te.setDuration(Integer.parseInt(typeOfExamDTO.getDuration()));
        te.setSpecialty(Specialty.valueOf(typeOfExamDTO.getSpecialty()));

        PriceItem priceItem = new PriceItem(AppointmentType.valueOf(typeOfExamDTO.getAppointmentType()),
                Double.parseDouble(typeOfExamDTO.getPrice()),Double.parseDouble(typeOfExamDTO.getDiscount()));

        priceItem.setTypeOfExam(te);
        //priceItemRepository.save(priceItem);
        te.setPriceItem(priceItem);
        //typeOfExamRepository.save(te);

        clinic.getTypeOfExams().add(te);
        clinicRepository.save(clinic);

        System.out.println(clinic.getTypeOfExams().size());

        return new MessageDTO("You successfully added new type of exam!!!", true);

    }


    public MessageDTO editTypeOfExam(TypeOfExamDTO typeOfExamDTO){

        TypeOfExam typeOfExam = typeOfExamRepository.findByName(typeOfExamDTO.getName());
        Clinic clinic = clinicRepository.findById(Long.parseLong(typeOfExamDTO.getClinicId()));

        List<Appointment> appointments = clinic.getAppointments()
                .stream().filter(a -> a.getTerm().getStartTime().after(new Timestamp(System.currentTimeMillis()))).collect(Collectors.toCollection(ArrayList::new));

        boolean hasOneAppointment = appointments.stream().filter(a -> a.getTypeOfExam().getName().equalsIgnoreCase(typeOfExam.getName())).count() > 0;


        if(hasOneAppointment){
            return new MessageDTO("Type of exam with this name has reserved appointment in this clinic!!!", false);
        }

        boolean hasRequestedAppointments = clinic.getAppointmentRequests()
                .stream().filter(ar -> ar.getTypeOfExam().getName().equalsIgnoreCase(typeOfExam.getName())).count() > 0;

        if(hasRequestedAppointments){
            return new MessageDTO("Type of exam with this name has requests for appointment in this clinic!!!", false);
        }


        typeOfExam.setDuration(Integer.parseInt(typeOfExamDTO.getDuration()));
        typeOfExam.getPriceItem().setPrice(Double.parseDouble(typeOfExamDTO.getPrice()));
        typeOfExam.getPriceItem().setDiscount(Double.parseDouble(typeOfExamDTO.getDiscount()));

        typeOfExamRepository.save(typeOfExam);
        return new MessageDTO("You successfully edited type of exam!!!", true);

    }

    public MessageDTO deleteTypeOfExam(TypeOfExamDTO typeOfExamDTO){

        TypeOfExam typeOfExam = typeOfExamRepository.findByName(typeOfExamDTO.getName());
        Clinic clinic = clinicRepository.findById(Long.parseLong(typeOfExamDTO.getClinicId()));

        List<Appointment> appointments = clinic.getAppointments()
                .stream().filter(a -> a.getTerm().getStartTime().after(new Timestamp(System.currentTimeMillis()))).collect(Collectors.toCollection(ArrayList::new));

        boolean hasOneAppointment = appointments.stream().filter(a -> a.getTypeOfExam().getName().equalsIgnoreCase(typeOfExam.getName())).count() > 0;

        System.out.println(clinic.getTypeOfExams().size());

        if(hasOneAppointment){
            return new MessageDTO("Type of exam with this name has reserved appointment in this clinic!!!", false);
        }

        boolean hasRequestedAppointments = clinic.getAppointmentRequests()
                .stream().filter(ar -> ar.getTypeOfExam().getName().equalsIgnoreCase(typeOfExam.getName())).count() > 0;

        if(hasRequestedAppointments){
            return new MessageDTO("Type of exam with this name has requests for appointment in this clinic!!!", false);
        }


        typeOfExamRepository.delete(typeOfExam);

        System.out.println(clinic.getTypeOfExams().size());
        return new MessageDTO("You successfully deleted type of exam!!!", true);

    }

}
