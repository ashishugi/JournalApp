<h1>MongoTemplate</h1>

1. Spring provides MongoTemplate, It is used to interact with the database - Mongo. Spring will autoconfigure it, we do not need to take care of it.
2. We can use Criteria to perform various type of queries based on criteria.
```
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

```