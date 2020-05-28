package com.ServisKlinickihCentara.repository;

import com.ServisKlinickihCentara.model.clinics.TypeOfExam;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.ArrayList;

public interface TypeOfExamRepository extends JpaRepository<TypeOfExam,Long> {

    ArrayList<TypeOfExam> findAll();

    TypeOfExam findByName(String name);
}
