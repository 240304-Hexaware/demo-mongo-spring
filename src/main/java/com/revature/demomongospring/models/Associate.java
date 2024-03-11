package com.revature.demomongospring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("associates")
public class Associate {
    @Field("_id")
    @MongoId(FieldType.OBJECT_ID)//helps spring understand how to utilize mongo's ID objects
    private ObjectId id;//with that above annotation we could use String in place of ObjectId type.

    @Field(name = "first_name")
    private String firstName;

    @Field(name = "last_name")
    private String lastName;

    public Associate() {
    }

    public Associate(ObjectId id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Associate(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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
