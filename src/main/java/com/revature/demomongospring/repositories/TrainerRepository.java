package com.revature.demomongospring.repositories;

import com.revature.demomongospring.models.Trainer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainerRepository extends MongoRepository<Trainer, ObjectId> {

}
