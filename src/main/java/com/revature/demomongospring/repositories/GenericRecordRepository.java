package com.revature.demomongospring.repositories;

import com.revature.demomongospring.models.GenericRecord;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenericRecordRepository extends MongoRepository<GenericRecord, ObjectId> {
}
