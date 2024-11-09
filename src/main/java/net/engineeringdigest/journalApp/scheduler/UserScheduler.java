package net.engineeringdigest.journalApp.scheduler;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserScheduler {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    // Use this to generate cron regex- http://www.cronmaker.com/;jsessionid=node04ei67t00gzl84dmccnlqueba811730.node0?0
    // OR use: https://www.freeformatter.com/cron-expression-generator-quartz.html
    @Scheduled(cron = "0 0 0 ? * *")
    public void fetchUserAndSentSentimentAnalysis() {
        List<User> userList = userRepositoryImpl.getUserForSentimentalAnalysis();

        for(User user : userList) {
            log.info("Running cron for the user: {}", user);
        }
    }
}
