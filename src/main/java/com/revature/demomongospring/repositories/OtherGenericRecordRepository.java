package com.revature.demomongospring.repositories;

import com.revature.demomongospring.models.GenericRecord;
import com.revature.demomongospring.models.OtherGenericRecord;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtherGenericRecordRepository  extends MongoRepository<OtherGenericRecord, ObjectId> {
}
