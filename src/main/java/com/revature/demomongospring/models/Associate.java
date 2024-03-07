package com.revature.demomongospring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Associate {
    @Id
    @Column
    private String _id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    public Associate() {
    }

    public Associate(String _id, String firstName, String lastName) {
        this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Associate(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
