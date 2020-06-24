package com.ServisKlinickihCentara.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ServisKlinickihCentara.repository.ClinicAdminRepository;
import com.ServisKlinickihCentara.repository.ClinicRepository;

public class ClinicEditService 
{
	@Autowired
	private ClinicRepository clinicRepository;
	
	@Autowired
	private ClinicAdminRepository clinicAdminRepository;
	
	
}
