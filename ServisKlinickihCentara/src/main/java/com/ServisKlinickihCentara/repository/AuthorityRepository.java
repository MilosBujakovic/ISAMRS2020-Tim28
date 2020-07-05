package com.ServisKlinickihCentara.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ServisKlinickihCentara.model.users.Authority;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    Authority findByName(String name);
}
