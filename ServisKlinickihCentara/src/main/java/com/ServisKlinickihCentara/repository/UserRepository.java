package com.ServisKlinickihCentara.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.users.User;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    User findById(long id);

    List<User> findAll();

    User findByuuid(String uuid);


}
