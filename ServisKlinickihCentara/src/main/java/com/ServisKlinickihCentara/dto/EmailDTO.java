package com.ServisKlinickihCentara.dto;


//dto za email jer se ne moze proslijediti email kao path varijabla u okviru url-a
public class EmailDTO {
    private String email;

    public EmailDTO(){
        super();
    }

    public EmailDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
