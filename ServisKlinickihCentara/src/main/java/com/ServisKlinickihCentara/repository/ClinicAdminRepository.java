package com.ServisKlinickihCentara.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.users.ClinicAdmin;

public interface ClinicAdminRepository extends JpaRepository<ClinicAdmin,Long> {
    ClinicAdmin findById(long id);

    ClinicAdmin findByEmail(String email);

    ArrayList<ClinicAdmin> findAll();
}