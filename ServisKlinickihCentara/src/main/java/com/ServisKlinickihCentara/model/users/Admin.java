package com.ServisKlinickihCentara.model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import java.sql.Timestamp;


@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    public Admin(){
        super();
    }

    public Admin(Long id, String email, String password, String name, String surname, boolean enabled, Timestamp lastPasswordResetDate) {
        super(id, email, password, name, surname, enabled, lastPasswordResetDate);
    }


}
