package com.app.service.dto;

import javax.validation.constraints.AssertTrue;

public class AuthorDto {

    int id;

    String firstName;

    String lastName;

    @AssertTrue
    public boolean isValid () {
        return this.firstName != null || this.lastName != null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
