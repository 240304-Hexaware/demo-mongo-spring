package com.revature.demomongospring.repositories;

import com.revature.demomongospring.models.Associate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyRepository extends MongoRepository<Associate, ObjectId> {//We could use String as the ID type, see Associate class
    /*
    saveOrUpdate()
    findById()
    findByFirstName(String firstName)
    findByLastName(String lastName)
    delete()
     */
    Optional<Associate> findByFirstName(String firstName);
}
