package com.ServisKlinickihCentara.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.users.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    User findById(long id);

    List<User> findAll();

    User findByuuid(String uuid);


}
