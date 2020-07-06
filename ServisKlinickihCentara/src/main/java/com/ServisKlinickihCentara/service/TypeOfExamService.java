package com.ServisKlinickihCentara.service;

import java.util.ArrayList;
import java.util.stream.Collectors;

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

}
