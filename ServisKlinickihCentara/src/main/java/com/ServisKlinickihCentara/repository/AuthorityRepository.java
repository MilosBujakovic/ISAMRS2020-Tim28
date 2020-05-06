package com.ServisKlinickihCentara.repository;

import com.ServisKlinickihCentara.model.users.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    Authority findByName(String name);
}
