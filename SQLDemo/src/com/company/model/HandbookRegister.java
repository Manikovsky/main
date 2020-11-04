package com.company.model;

public class HandbookRegister extends BaseRegister {
    private String firstName;
    private String lastName;
    private String numberPhone;

    public HandbookRegister(long id, String firstName, String lastName, String numberPhone) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberPhone = numberPhone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
}
