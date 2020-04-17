package com.ServisKlinickihCentara.controller;


import com.ServisKlinickihCentara.dto.MessageDTO;
import com.ServisKlinickihCentara.dto.PatientUpdateDTO;
import com.ServisKlinickihCentara.model.Authority;
import com.ServisKlinickihCentara.model.Patient;
import com.ServisKlinickihCentara.model.User;
import com.ServisKlinickihCentara.model.UserTokenState;
import com.ServisKlinickihCentara.security.TokenHelper;
import com.ServisKlinickihCentara.security.auth.JwtAuthenticationRequest;
import com.ServisKlinickihCentara.service.CustomUserDetailsService;
import com.ServisKlinickihCentara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping( value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE )
public class AuthenticationController  {

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;


    //Ukoliko je aplikacija podignuta lokalno => localhost:8080/api/login
    //Metodi se prosledjuje objekat u kom se nalazi username i password korisnika
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest,
            HttpServletResponse response
    ) throws AuthenticationException, IOException {

        System.out.println("login");
        System.out.println(authenticationRequest.getUsername());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encoded =  passwordEncoder.encode(authenticationRequest.getPassword());
        System.out.println(encoded);
        // Izvrsavanje security dela
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        // Ubaci username + password u kontext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token
        User user = (User)authentication.getPrincipal();

        System.out.println("email: "  + user.getEmail() + "\npassword: " + user.getPassword());
        String jws = tokenHelper.generateToken(user.getEmail());
        int expiresIn = tokenHelper.getExpiredIn();

        ArrayList<Authority> authorities = user.getAuthorities().stream().map(authority->(Authority) authority).collect(Collectors.toCollection(ArrayList::new));

        // Vrati token kao odgovor na uspesno autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jws, expiresIn, authorities.get(0).getAuthority()));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<?> refreshAuthenticationToken(
            HttpServletRequest request,
            HttpServletResponse response,
            Principal principal
    ) {

        String authToken = tokenHelper.getToken( request );


        if (authToken != null && principal != null) {

            // TODO check user password last update
            String refreshedToken = tokenHelper.refreshToken(authToken);
            int expiresIn = tokenHelper.getExpiredIn();
            return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
        } else {
            UserTokenState userTokenState = new UserTokenState();
            return ResponseEntity.accepted().body(userTokenState);
        }
    }

    @RequestMapping(value = "/getLoggedPatient", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PatientUpdateDTO> currentUserName(Authentication authentication) {
        Patient patient = (Patient) userService.findByUsername(authentication.getName());
        PatientUpdateDTO patientUpdateDTO = new PatientUpdateDTO(patient);
        return new ResponseEntity<PatientUpdateDTO>(patientUpdateDTO,HttpStatus.OK);
    }


    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
        System.out.println("changing password");
        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
        Map<String, String> result = new HashMap<>();
        result.put( "result", "success" );
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        String jwt = tokenHelper.generateToken(authentication.getName());
        int expiresIn = tokenHelper.getExpiredIn();
       //authority authority = authentication.getAuthorities().stream().map(a->(Authority) a).collect(Collectors.toCollection(ArrayList::new)).get(0);
        return new ResponseEntity<>(new UserTokenState(jwt, expiresIn), HttpStatus.OK);

        //return ResponseEntity.accepted().body(result);

    }





    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ResponseEntity<MessageDTO> logout (HttpServletRequest request, HttpServletResponse response) {
        System.out.println("logout");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ResponseEntity<MessageDTO>(new MessageDTO("You have been logout.",true), HttpStatus.OK);
    }

    static class PasswordChanger {
        public String oldPassword;
        public String newPassword;
    }
}
