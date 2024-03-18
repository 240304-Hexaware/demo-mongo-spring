package com.revature.demomongospring.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.ArrayList;
import java.util.List;

@Document("trainers")
public class Trainer {
    @Field("_id")
    @MongoId(FieldType.OBJECT_ID)
    private ObjectId id;

    @Field
    @DocumentReference
    private List<Associate> associates;

    public Trainer() {
        this.associates = new ArrayList<>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<Associate> getAssociates() {
        return associates;
    }

    public void setAssociates(List<Associate> associates) {
        this.associates = associates;
    }
}
