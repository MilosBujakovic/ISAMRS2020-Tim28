package com.ServisKlinickihCentara.service;

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

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class TypeOfExamService {

    @Autowired
    private TypeOfExamRepository typeOfExamRepository;

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
