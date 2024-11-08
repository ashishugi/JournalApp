package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSentimentalAnalysis() {
        Query query = new Query();

        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

// Criteria criteria = new Criteria();
//        query.addCriteria(criteria.andOperator(
//                Criteria.where("").exists(true),
//                Criteria.where("sentiment").is(true)
//        ));
//        query.addCriteria(Criteria.where("email").ne(null).ne(""));

        return mongoTemplate.find(query, User.class);
    }
}