package com.ServisKlinickihCentara.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.clinics.TypeOfExam;

public interface TypeOfExamRepository extends JpaRepository<TypeOfExam,Long> {

    ArrayList<TypeOfExam> findAll();

    TypeOfExam findByName(String name);
}
