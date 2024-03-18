package com.revature.demomongospring.models;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("records")
public class GenericRecord extends org.bson.Document{

    @Field("_id")
    @MongoId(FieldType.OBJECT_ID)
    private ObjectId id;

    public GenericRecord() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
