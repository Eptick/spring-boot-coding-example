## Dependencies
postgresql 13+
Java 11 +
Maven
## Running the application
Create your PostgreSQL database and user
Adjust the datasource properties in the `src/main/resources/application.properties`
**Run the authorization server**

Run this command from the terminal in the source folder `mvnw spring-boot:run`
Get the access token from the

## Deploying to production
Adjust the datasource and authorization server properties using enviroment variables   
Use `mvnw springBoot:install` to build a `war` located in
`target\library-{version}.war` and use it to be deployed to your production
server, be that Tomcat or other methods


## Retrieving the token
```
curl http://localhost:8888/token?scope=admin
curl http://localhost:8888/token?scope=author
```

## Decision
### fixedRate instead of anything else
We want the notification to run on app startup, if something fails, when we
start the app we want it to notify about latest books
The alternative was cron "0 * * * *" but the specific time execution was not
neccecary here, it causes problems if not anything else
