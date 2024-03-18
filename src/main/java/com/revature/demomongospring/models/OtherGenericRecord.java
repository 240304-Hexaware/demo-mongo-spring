package com.revature.demomongospring.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashMap;
import java.util.Map;

/**
 * This implementation of a generic mongo record object to be persisted is a little less ideal than the
 * GenericRecord class. This will persist just fine and we can work with it, but the document will contain
 * a map object rather than just having the key/value pairs.
 */
@Document("records")
public class OtherGenericRecord {
    @Field("_id")
    @MongoId(FieldType.OBJECT_ID)
    private ObjectId id;

    @Field("map")
    private Map<String, Object> objMap;

    public OtherGenericRecord() {
        this.objMap = new HashMap<>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void append(String key, Object value) {
        this.objMap.put(key, value);
    }
}
