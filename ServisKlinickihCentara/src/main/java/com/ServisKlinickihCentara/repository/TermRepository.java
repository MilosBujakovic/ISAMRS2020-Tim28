package com.ServisKlinickihCentara.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.clinics.Term;

public interface TermRepository extends JpaRepository<Term,Long>
{
	Term findById(long id);
	List<Term> findAll();
}
