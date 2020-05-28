package com.ServisKlinickihCentara.service;

import com.ServisKlinickihCentara.model.clinics.TypeOfExam;
import com.ServisKlinickihCentara.repository.TypeOfExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class TypeOfExamService {

    @Autowired
    private TypeOfExamRepository typeOfExamRepository;


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

}
