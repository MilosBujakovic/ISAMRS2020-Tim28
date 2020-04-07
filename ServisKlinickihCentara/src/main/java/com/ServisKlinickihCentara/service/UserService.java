package com.ServisKlinickihCentara.service;

import com.ServisKlinickihCentara.model.User;
import com.ServisKlinickihCentara.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.nio.file.AccessDeniedException;
import java.util.List;

public class UserService {
    @Autowired
    private UserRepository userRepository;


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
