package com.ServisKlinickihCentara.service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.typeOfExamDTO.TypeOfExamDTO;
import com.ServisKlinickihCentara.model.clinics.PriceItem;
import com.ServisKlinickihCentara.model.clinics.TypeOfExam;
import com.ServisKlinickihCentara.model.enums.AppointmentType;
import com.ServisKlinickihCentara.model.enums.Specialty;
import com.ServisKlinickihCentara.repository.PriceItemRepository;
import com.ServisKlinickihCentara.repository.TypeOfExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.clinics.TypeOfExam;
import com.ServisKlinickihCentara.repository.ClinicRepository;
import com.ServisKlinickihCentara.repository.TypeOfExamRepository;

@Service
public class TypeOfExamService {

    @Autowired
    private TypeOfExamRepository typeOfExamRepository;
    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private PriceItemRepository priceItemRepository;


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
        //ArrayList<TypeOfExam> typeOfExams = typeOfExamRepository.findAll();

        TypeOfExam typeOfExam = typeOfExamRepository.findByName(typeOfExamDTO.getName().toLowerCase());

        if(typeOfExam != null){
            return new MessageDTO("Type of exam whith this name already exists!!!", false);
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
        typeOfExamRepository.save(te);

        return new MessageDTO("You successfully added new type of exam!!!", true);


    }

}
