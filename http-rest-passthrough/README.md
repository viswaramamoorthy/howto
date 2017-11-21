# Restful service integration

Restful service pass through to show service to service integration. Restful services, in this application, are done using Camel Rest as well as Spring MVC to show two different options.

Users API, from https://jsonplaceholder.typicode.com/, is the source of data for the restful services in this application. Jackson JSON library used to convert response into Json that caller can use. 

Note that Maven wrapper is part of the project folder. A Java JDK 8 home environment variable, pointing to Java JDK 8, needed to use Maven commands to build, deploy and run maven goals

### Build & Deploy

# $ ./mvnw clean verify
Builds, runs tests and verifies that code coverage goal is met. Jacoco Maven plugin used to check code coverage. A Jacoco rule is configured to have 70% branch coverage & 80% lines coverage. Coverage reports are under ${project home}/target/site/

# $ ./mvnw clean package
Builds, runs tests and packages WAR file

# $ ./mvnw tomcat7:run-war-only
To deploy application to Tomcat 7

# $ ./mvnw eclipse:clean eclipse:eclipse
To prepare eclipse project

### APIs

  * Option 1 - Spring MVC and rest controller used to host rest APIs. URLs with pattern http://localhost:8080/spring/* used to access these APIs
  
  * Option 2 - Rest component configred via Camel rest and it uses Swagger to host rest APIs. URLs with pattern http://localhost:8080/camel/* used to access these APIs
  
  * Destination service is configured via Camel HTTP component and processors used to process request and prepare response

Since two different technologies used to demonstrate restful services hosted by this application, two different sets of URLs are shown to execute. 

# Spring security
Spring security basic authentication enabled for this application. To access below APIs, please use "admin/password" as user and password

# Spring based restful APIs
  * User list API
    http://localhost:8080/spring/users/list
	
  * Users by user type API
    http://localhost:8080/spring/users/list/{username} 
	Example: http://localhost:8080/spring/users/list/Bret

# Camel rest based restful APIs
  * User list API
    http://localhost:8080/camel/users/list
	
  * Users by user type API
    http://localhost:8080/camel/users/list/{username}
	Example: http://localhost:8080/camel/users/list/Bret
	
