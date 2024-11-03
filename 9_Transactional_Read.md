<h1>Transactional - Annotation</h1>

1. Consider below code: 
    ```java
        public void saveJournalEntry(JournalEntry journalEntry, String username) {
        User user = userService.findByUsername(username).orElse(null);

        if(user == null) { return; }

        journalEntry.setDate(LocalDateTime.now());
        JournalEntry savedJournalEntry = this.journalRepository.save(journalEntry);
        user.getJournalEntries().add(savedJournalEntry);
        userService.saveUser(user);
    } 
   ```
   1. What if journalEntry get save in the journal_entries db, but before updating its reference in User table the some exception occurs, due to which reference was not update in the User table.
      Hence, this created the inconsistency.
   2. In above scenario journalEntry will be saved in journal_entries db but its reference will not be associated with any user. Ideally this should not happen.
   3. Either entry should be inserted in both journal_entries and user db or should be inserted in any of them (Atomicity - ACID)
2. ```@Transactional```: Use this annotation over method. This will make the method success only if all the line present inside it gets success or else it will rollback the other success line if any failure occur.
3. ```@EnableTransactionManagement``` : This annotation is used to enable ```@Transactional``` in the application. Placed at the main files. It tells spring to collect all the methods having @Transactional.
4. Also to manage these - spring create a separate container and treat it as single operation. This will work fine for multiple parallel/concurrent request. As spring will make separate container to manage them.
   ```java
          @Bean
          public PlatformTransactionManager platformTransactionManager(MongoDatabaseFactory mongoDatabaseFactory) {
             return new MongoTransactionManager(mongoDatabaseFactory);
          }
   ```
   1. PlatformTransactionManager is an interface will manages these transaction, and will required and implementation to manage. In Mongo we have MongoTransactionManager for this purpose, hence we have created bean for it.
5. Note: In mongoDb - to use @Transactional we need a replica-set to perform this action. transactions are built on concepts of logical sessions they require mecahnics (like oplog) which are only available in replica set environment.
6. Hence, in local you need to either set up replica or we need to use mongoDb atlas.
7. How does it works ?
   1. Data is temporarily saved and if all the statement run fine, then this temporary changes are commited and made permanent.