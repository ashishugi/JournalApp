<h1>Logging</h1>


1. Logging can help to debug your application faster.
2. Spring Boot supports various logging framework - logback, log4j2, Java util logging (JUL)
   1. Logback - It is default logging mechanism in many spring boot application. It offers flexible configuration and good performance. Mostly used.
   2. log4j2 - It has features like asynchronous and supports various output formats.
   3. JUL - It is default logging framework included in java standard edition, has less features.
3. Logback - to customise the logging configuration we need make the logback-spring.xml or logback.xml file in src/main/resources. It will be used instead on default logging configuration.
4. Different logging levels: 
   1. TRACE
   2. DEBUG
   3. INFO
   4. WARN
   5. ERROR
5. ```Slf4j``` and ```Log4j2```: These are the two annotations provided by spring boot, these helps to automatically inject the logger instances to the classes.
6. We will be using logback via Slf4j.
7. By default ERROR, WARN, INFO are available. To make other logging level enable we need to do some changes.\
   1. to enable remaining TRACE and DEBUG use : 
        ```
            logging:
              level:
                root: DEBUG
      ```
8. How to use?
   1. We can simply use ```@Slf4j``` annotation at class level and use it as - ```log.info("some message {} ", username)```
9. Using logback.xml:
    ```
        <configuration>
            <appender name="myConsoleAppender" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>
                %d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
            </pattern>
        </encoder>
    </appender>
    <appender name="myFileAppender" class="ch.qos.logback.core.FileAppender">
        <encoder>
            <pattern>
                %d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
            </pattern>
        </encoder>
        <file>
            journalApp.log
        </file>
    </appender>

    <root level="INFO">
        <appender-ref ref="myConsoleAppender" />
        <appender-ref ref="myFileAppender" />
    </root>
         </configuration>
   ```
   1. Appender - which where to print the logs, basically to tell where to output the logs.
   2. encoder - shows which format to print the logs.
   3. right now using fileAppender simply use the same and file and append all the logs, what if we need to make different - different files based on time or size etc.
   4. User 
10. 