<h1>Spring Security</h1>

1. It handles authentication and authorization inside our application.
   1. authentication - if able to login or not
   2. authorization - what roles does loggedIn user has, admin or simple access ? or only read access or write access.
2. Configuration:
   ```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
   ```
   1. using above dependency will secure each and every end point, and for accessing any end point we would need username and password.
3. By default Spring Security uses - http basic authentication,
   1. Http basic authentication - here we send Authorization header from client/frontend/postman - ```Authorization: Basic <encoded-string>```, server/backend then decodes the string, extract the required things.ex: username and password.
      if the username and password is correct, then access is granted else Unauthorized error is given/thrown back
   2. encoded-string - encoded in base64.
   3. If you have not created any user at your then spring will automatically create user and print password in console logs for your usage, and username will ```user```
4. 