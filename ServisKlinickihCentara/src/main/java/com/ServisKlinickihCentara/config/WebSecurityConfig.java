package com.ServisKlinickihCentara.config;

import com.ServisKlinickihCentara.security.TokenHelper;
import com.ServisKlinickihCentara.security.auth.RestAuthenticationEntryPoint;
import com.ServisKlinickihCentara.security.auth.TokenAuthenticationFilter;
import com.ServisKlinickihCentara.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //Implementacija PasswordEncoder-a koriscenjem BCrypt hashing funkcije.
    //BCrypt po defalt-u radi 10 rundi hesiranja prosledjene vrednosti.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService jwtUserDetailsService;

    //Neautorizovani pristup zastcenim resursima
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    //Definisemo nacin autentifikacije
    //Svaki
    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( jwtUserDetailsService )
                .passwordEncoder( passwordEncoder() );
    }

    @Autowired
    TokenHelper tokenHelper;


    //Definisemo prava pristupa odredjenim URL-ovima
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //komunikacija izmedju klijenta i servera je stateless
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
                //za neautorizovane zahteve posalji 401 gresku
                .exceptionHandling().authenticationEntryPoint( restAuthenticationEntryPoint ).and()
                .authorizeRequests()
                //svim korisnicima dopusti da pristupe putanjama /auth/**
                //.antMatchers("/auth/**").permitAll()
                    .antMatchers("/auth/change-password").permitAll()
                    .antMatchers("/user/acceptPatient/**").hasAuthority("SYSTEM_ADMIN")
                    .antMatchers("/user/rejectPatient/**").hasAuthority("SYSTEM_ADMIN")
                    .antMatchers("/user/getUnregisteredPatients").hasAuthority("SYSTEM_ADMIN")
                    .antMatchers("/user/updatePatient").hasAuthority("PATIENT")
                    .antMatchers("/auth/getLoggedPatient").hasAuthority("PATIENT")
                    .antMatchers("/clinic/getClinicsForBasicView").hasAuthority("PATIENT")
                    .antMatchers("/clinic/basicFilterSortingClinics/**").hasAuthority("PATIENT")
                    .antMatchers("/clinic/getSpecialities").hasAuthority("PATIENT")
                    .antMatchers("/appointment/getPredefinedAppointments/**").hasAuthority("PATIENT")
                    .antMatchers("/appointment/quickAppointmentReservation").hasAuthority("PATIENT")
                    .antMatchers("/appointment/getPatientsAppointments").hasAuthority("PATIENT")
                    .antMatchers("/appointment/cancelAppointment/**").hasAuthority("PATIENT")
                    .antMatchers("/clinic/getClinicsByAdvancedSearch").hasAuthority("PATIENT")
                    .antMatchers("/clinic/filterExistingAdvancedSearchedItems").hasAuthority("PATIENT")
                    .antMatchers("/medicalRecord/getMedicalRecord").hasAuthority("PATIENT")
                    .antMatchers("/appointment/getPatientsHistory").hasAuthority("PATIENT")
                    .antMatchers("/appointment/filterSortingPatientsHistory").hasAuthority("PATIENT")
                    .antMatchers("/doctor/getForClinicDoctorsFreeSlots").hasAuthority("PATIENT")
                    .antMatchers("/clinicRating/evaluateClinicByPatient").hasAuthority("PATIENT")
                    .antMatchers("/doctorRating/evaluateDoctorByPatient").hasAuthority("PATIENT")
                    .antMatchers("/doctor/filterExistingDoctors").hasAuthority("PATIENT")
                    .antMatchers("/doctor/getClinicAndDoctor").hasAuthority("PATIENT")
                    .antMatchers("/typeOfExam/getTypeOfExams").permitAll()
                    .antMatchers("/typeOfExam/getTypeOfExamsWithoutOperations").permitAll()
                    .antMatchers("/clinic/getClinicNameAddressRating/**").hasAuthority("PATIENT")
                    .antMatchers("/clinic/checkClinicHasFreeDoctorsForSpecificDateAndTypeOfExam/**").hasAuthority("PATIENT")
                    .antMatchers("/appointment/customAppointmentReservation").hasAuthority("PATIENT")
                    .antMatchers("/auth/logout").permitAll()
                    .antMatchers("/auth/refresh").permitAll()

                //svaki zahtev mora biti autorizovan
                .anyRequest().authenticated().and()
                //presretni svaki zahtev filterom
                .addFilterBefore(new TokenAuthenticationFilter(tokenHelper, jwtUserDetailsService), BasicAuthenticationFilter.class);

        http.csrf().disable();
    }


    //Generalna bezbednost aplikacije
    @Override
    public void configure(WebSecurity web) throws Exception {
        // TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
        web.ignoring().antMatchers(
                HttpMethod.POST,
                "/auth/login"
        );
        web.ignoring().antMatchers(
                HttpMethod.POST,
                "/user/register"
        );
        web.ignoring().antMatchers(
                HttpMethod.GET,
                "/user/activatePatient/**"
        );
        web.ignoring().antMatchers(
                HttpMethod.GET,
                "/appointment/acceptQuickAppointment/**"
        );
        web.ignoring().antMatchers(
                HttpMethod.GET,
                "/appointment/declineQuickAppointment/**"
        );
        web.ignoring().antMatchers(
                HttpMethod.GET,
                "/",
                "/webjars/**",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/**/*.jpg"
        );

    }
}
