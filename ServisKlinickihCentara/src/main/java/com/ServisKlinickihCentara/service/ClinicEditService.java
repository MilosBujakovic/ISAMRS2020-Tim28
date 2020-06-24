package com.ServisKlinickihCentara.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ServisKlinickihCentara.dto.clinicsDTO.EditClinicDTO;
import com.ServisKlinickihCentara.model.clinics.Clinic;
import com.ServisKlinickihCentara.model.users.ClinicAdmin;
import com.ServisKlinickihCentara.repository.ClinicAdminRepository;
import com.ServisKlinickihCentara.repository.ClinicRepository;

@Service
public class ClinicEditService 
{
	@Autowired
	private ClinicRepository clinicRepository;
	
	@Autowired
	private ClinicAdminRepository clinicAdminRepository;
	
	@Autowired
	private UserService userService;
	
	
	public EditClinicDTO getClinicBasics(String email)
	{
		ClinicAdmin admin = (ClinicAdmin) userService.findByUsername(email);
		Clinic clinic = clinicRepository.getOne(admin.getClinic().getId());
		EditClinicDTO clinicDTO = new EditClinicDTO(clinic);
		return clinicDTO;
	}
	
	public void updateClinicBasics(EditClinicDTO clinicDTO)
	{
		Clinic clinic = clinicRepository.findById(clinicDTO.getId());
		
		clinic.setName(clinicDTO.getName() );
		clinic.setDescription(clinicDTO.getDescription());
		clinic.setAddress(clinicDTO.getAddress());
		clinicRepository.save(clinic);
	}
	
}
