<h1>JWT</h1>


1. JWT is a way to securely transmit information between two parties as JSON object.
2. 3 Parts:
   1. Header: Algorithm, and type (JWT)
   2. Payload: data
   3. Signature: signature to verify if data is transmitted and received unchanged
3. dependencies:
   ```xml
      <!-- JWT dependencies -->

		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.12.6</version>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.12.6</version>
		</dependency>

		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.12.6</version>
			<scope>runtime</scope>
		</dependency>
   ```
4. jwtFilter will run before any other things --> and it will set the springSecurity context for further usage else it will restrict the api access. We need to generate JWT token before accessing any non-public api.  These to be put inside the bearer token at the time of request.