<h1>CRON/ SCHEDULER</h1>


1. To make scheduler you can use - ```@Scheduled```, and also to let spring boot know that there are methods that are using scheduled methods ```@EnableScheudling```
```java

// Use this to generate cron regex- http://www.cronmaker.com/;jsessionid=node04ei67t00gzl84dmccnlqueba811730.node0?0
// OR use: https://www.freeformatter.com/cron-expression-generator-quartz.html
@Scheduled(cron = "0 * * * * ?")
public void fetchUserAndSentSentimentAnalysis() {
    List<User> userList = userRepositoryImpl.getUserForSentimentalAnalysis();

    for(User user : userList) {
        log.info("Running cron for the user: {}", user);
    }
}

@SpringBootApplication
@EnableScheduling
public class JournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);
    }
}

```