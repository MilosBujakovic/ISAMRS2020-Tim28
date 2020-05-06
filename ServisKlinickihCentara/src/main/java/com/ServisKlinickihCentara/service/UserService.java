package com.ServisKlinickihCentara.service;

import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.PatientDTO;
import com.ServisKlinickihCentara.dto.PatientUpdateDTO;
import com.ServisKlinickihCentara.dto.UnregisteredPatientDTO;
import com.ServisKlinickihCentara.model.patients.Patient;
import com.ServisKlinickihCentara.model.users.Authority;
import com.ServisKlinickihCentara.model.users.User;
import com.ServisKlinickihCentara.repository.AuthorityRepository;
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
import java.util.UUID;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private  EmailService emailService;


    public MessageDTO registratePatient(PatientDTO patientDTO){

        if (patientDTO.getAddress().trim().equals("") || patientDTO.getCity().trim().equals("") ||
                patientDTO.getCountry().trim().equals("") || patientDTO.getEmail().trim().equals("") ||
                patientDTO.getName().trim().equals("") || patientDTO.getSurname().trim().equals("") ||
                patientDTO.getPassword1().trim().equals("") || patientDTO.getPassword2().trim().equals("") ||
                patientDTO.getInsurance_number().trim().equals("") || patientDTO.getPhone_number().trim().equals("")){
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

        try {
            Integer.parseInt(patientDTO.getPhone_number());
        } catch (NumberFormatException e){
            return new MessageDTO("Phone number must contains only numbers!", false);
        }

        patient.setPhoneNumber(patientDTO.getPhone_number());
        patient.setInsuranceNumber(patientDTO.getInsurance_number());
        patient.setEnabled(false);
        patient.setUuid(UUID.randomUUID().toString());

        patientRepository.save(patient);
        return new MessageDTO("Request for registration has been successfully sent :)", true);
    }

    public MessageDTO updatePatient(PatientUpdateDTO patientUpdateDTO){

        if (patientUpdateDTO.getAddress().trim().equals("") || patientUpdateDTO.getCity().trim().equals("") ||
                patientUpdateDTO.getCountry().trim().equals("") || patientUpdateDTO.getEmail().trim().equals("") ||
                patientUpdateDTO.getName().trim().equals("") || patientUpdateDTO.getSurname().trim().equals("") ||
                patientUpdateDTO.getPhone().trim().equals("")){
            return new MessageDTO("All attributes fields must be filled!!!", false);
        }

        Patient patient = patientRepository.findByEmail(patientUpdateDTO.getEmail());


        if(patient == null){
            return new MessageDTO("Something is wrong, patient with this email doesn't exist!",false);
        }


        patient.setName(patientUpdateDTO.getName());
        patient.setSurname(patientUpdateDTO.getSurname());
        patient.setCity(patientUpdateDTO.getCity());
        patient.setAddress(patientUpdateDTO.getAddress());
        patient.setCountry(patientUpdateDTO.getCountry());


        if(!patient.getPhoneNumber().equalsIgnoreCase(patientUpdateDTO.getPhone())){
            Patient p = patientRepository.findByPhoneNumber(patientUpdateDTO.getPhone());
            if(p != null){
                return new MessageDTO("Other patient has phone number that you entered!", false);
            }
        }

        try {
            Integer.parseInt(patientUpdateDTO.getPhone());
        } catch (NumberFormatException e){
            return new MessageDTO("Phone number must contains only numbers!", false);
        }

        patient.setPhoneNumber(patientUpdateDTO.getPhone());


        patientRepository.save(patient);
        return new MessageDTO("Data was successfully changed :)",true);
    }


    public ArrayList<UnregisteredPatientDTO> getUnregisteredPatients() {
        List<Patient> patients = patientRepository.findAll();

        ArrayList<UnregisteredPatientDTO> unregisteredPatients = new ArrayList<UnregisteredPatientDTO>();

        for(Patient patient: patients){
            if(!patient.isEnabled()){
                unregisteredPatients.add(new UnregisteredPatientDTO(patient));
            }
        }

        return unregisteredPatients;

    }

    public void sendActivationLink(String email){
        Patient patient = patientRepository.findByEmail(email);
        this.emailService.sendMail("slavengaric@gmail.com","Activation link for registration","Please click below to activate your account \nhttp://localhost:8080/user/activatePatient/" + patient.getUuid());
    }


    public void setPatientActive(String uuid) throws UsernameNotFoundException {
        User user = userRepository.findByuuid(uuid);
        Patient patient = (Patient) user;
        Authority authority = authorityRepository.findByName("PATIENT");
        ArrayList<Authority> authorities = new ArrayList<>();
        authorities.add(authority);
        patient.setAuthorities(authorities);
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

    public User findByUuid(String uuid ) {
        User u = userRepository.findByuuid(uuid);
        return u;
    }

    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }


}
