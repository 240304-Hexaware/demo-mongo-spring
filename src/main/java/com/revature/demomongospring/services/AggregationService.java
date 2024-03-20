package com.revature.demomongospring.services;

import com.revature.demomongospring.models.Associate;
import com.revature.demomongospring.repositories.AggregationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AggregationService {

    private final AggregationRepository aggregationRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AggregationService(AggregationRepository aggregationRepository, MongoTemplate mongoTemplate) {
        this.aggregationRepository = aggregationRepository;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Using MongoTemplate
     */
    public List<Associate> findWithMongoTemplate() {
        Query query = new Query();
        query.addCriteria(Criteria.where("first_name").is("Andrew"));
        query.addCriteria(Criteria.where("age").gte(25));
        query.fields()
                .include("first_name")
                .include("last_name")
                .exclude("id");
        return mongoTemplate.find(query, Associate.class);
    }

    /**
     *
     * @return
     */
    public List<Associate> findWithMongoExpression() {
       return aggregationRepository.findWithMongoExpression();//See the repository interface
    }

    public AggregationResults<Associate> findWithAggregation() {
        MatchOperation matchStage = Aggregation.match(new Criteria("first_name").is("Andrew"));
        ProjectionOperation projectionStage = Aggregation.project("first_name", "last_name");
        //Examples of other stages we could use, see mongodb docs:
        SortOperation sortStage;
        GroupOperation groupStage;
        CountOperation countOperation;
        LimitOperation limitOperation;
        MergeOperation mergeOperation;
        SkipOperation skipOperation;
        //...and more. Check the contents of the org.springframework.data.mongodb.core.aggregation package
        //and see the stages in mongodb docs: https://www.mongodb.com/docs/manual/reference/operator/aggregation-pipeline/


        Aggregation pipeline = Aggregation.newAggregation(matchStage, projectionStage);

        AggregationResults<Associate> results = mongoTemplate.aggregate(pipeline, "associates", Associate.class);
        return results;
    }


}
