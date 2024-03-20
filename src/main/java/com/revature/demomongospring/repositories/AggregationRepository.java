package com.revature.demomongospring.repositories;

import com.revature.demomongospring.models.Associate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Here we will see some examples of using aggregation pipelines in spring with mongo
 */
@Repository
public interface AggregationRepository extends MongoRepository<Associate, ObjectId> {

    /**
     * This is the way you can write queries as mongo expressions, just like writing SQL expressions in spring data JPA
     * when we are using relational databases
     */
    @Query(value="{first_name: \"Andrew\"}", fields="{first_name: 1, last_name: 1, _id: 0}")
    public List<Associate> findWithMongoExpression();
}
