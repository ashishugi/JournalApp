<h1>SonarQube / SonarLint</h1>


1. Ways to use sonarQube:
   1. Download locally
   2. Deploy your code on sonar server
   3. Use sonar cloud
2. Locally:
   ```
      <!-- SonarQube plugin -->
      <plugin>
        <groupId>org.sonarsource.scanner.maven</groupId>
        <artifactId>sonar-maven-plugin</artifactId>
        <version>3.7.0.1746</version>
      </plugin>
   ```
   1. Run sonal locally at 9000 port(default)
   2. ```mvn clean install sonar:sonar```
   3. 