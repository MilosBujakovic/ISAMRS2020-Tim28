package com.ServisKlinickihCentara.service;

import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.PatientDTO;
import com.ServisKlinickihCentara.model.Patient;
import com.ServisKlinickihCentara.model.User;
import com.ServisKlinickihCentara.repository.PatientRepository;
import com.ServisKlinickihCentara.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    public MessageDTO registratePatient(PatientDTO patientDTO){

        if (patientDTO.getAddress().trim() == "" || patientDTO.getCity().trim() == "" ||
                patientDTO.getCountry().trim() == "" || patientDTO.getEmail().trim() == "" ||
                patientDTO.getName().trim() == "" || patientDTO.getSurname().trim() == "" ||
                patientDTO.getPassword1().trim() == "" || patientDTO.getPassword2().trim() == "" ||
                patientDTO.getInsurance_number().trim() == "" || patientDTO.getPhone_number().trim() == ""){
            return new MessageDTO("All fields must be filled!!!", false);
        }

        Patient p = patientRepository.findByEmail(patientDTO.getEmail());

        if (p != null){
            return new MessageDTO("The entered email already exists!!!", false);
        }

        p = patientRepository.findByPhoneNumber(patientDTO.getPhone_number());

        if (p != null){
            return new MessageDTO("The entered phone number already exists!!!", false);
        }

        p = patientRepository.findByInsuranceNumber(patientDTO.getInsurance_number());

        if (p != null){
            return new MessageDTO("The entered insurance number already exists!!!", false);
        }

        if (!patientDTO.getPassword1().equals(patientDTO.getPassword2())){
            return new MessageDTO("Passwords don't match!!!", false);
        }


        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Patient patient = new Patient();
        patient.setEmail(patientDTO.getEmail());
        patient.setName(patientDTO.getName());
        patient.setSurname(patientDTO.getSurname());
        patient.setPassword(passwordEncoder.encode(patientDTO.getPassword1()));
        patient.setCity(patientDTO.getCity());
        patient.setAddress(patientDTO.getAddress());
        patient.setCountry(patientDTO.getCountry());
        patient.setPhoneNumber(patientDTO.getPhone_number());
        patient.setInsuranceNumber(patientDTO.getInsurance_number());
        patient.setEnabled(false);

        patientRepository.save(patient);
        return new MessageDTO("Request for registration has been successfully sent :)", true);
    }

    public void setPatientActive(String email ) throws UsernameNotFoundException {
        Patient patient = patientRepository.findByEmail(email);
        patient.setEnabled(true);
        patientRepository.save(patient);
    }

    public void deletePatient(String email){
        Patient patient = patientRepository.findByEmail(email);
        patientRepository.delete(patient);
    }


    public User findByUsername(String username ) throws UsernameNotFoundException {
        User u = userRepository.findByEmail( username );
        return u;
    }

    public User findById( Long id ) throws AccessDeniedException {
        User u = userRepository.findById((long)id);
        return u;
    }

    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }


}
