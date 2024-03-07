package com.revature.demomongospring.repositories;

import com.revature.demomongospring.models.Associate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends MongoRepository<Associate, Integer> {
    //nope
}
