## Dependencies
- Postgresql 13+
- Java 11 +
- Maven
## Running the application
1. Create your PostgreSQL database and user
1. Adjust the datasource properties in the `src/main/resources/application.properties`   
1. **Run the authorization server**

1. Run this command from the terminal in the source folder `mvnw spring-boot:run`
1. Get the access token from the method described below
## Retrieving the token
```
curl http://localhost:8888/token?scope=admin
curl http://localhost:8888/token?scope=author
```
## Deploying to production
Adjust the datasource and authorization server properties using enviroment variables   
Use `mvnw springBoot:install` to build a `war` located in
`target\library-{version}.war` and use it to be deployed to your production
server, be that Tomcat or other methods

## Decision
### fixedRate for @Scheduled instead of anything else
We want the notification to run on app startup, if something fails, when we
start the app we want it to notify about latest books
The alternative was cron "0 * * * *" but the specific time execution was not
neccecary here, it causes problems if not anything else
### Api versioning using Path versions
The versioning system chosen was `/v1/**`. This in addition to the `@ApiVersion`
annotation allows us to easly version not only our Controllers but also methods
inside the controllers. The alternatives were:
- Header versioning
    - outdated
    - Requires custom headers
    - hard for the consumers to pass and control the versions
- Query versioning
    - Introduce confusion inside the valid query parameters
- Content negotiation
    - Difficult to test the API
### Querydsl
Querydsl was chosen to provide a near out of the box solution for filtering the
data. It requires some modification of the repositories but it is usefull
trought the application for filtering of data. The alternatives were:
- JPA Queries
    - Lot's of overhead and code that can lead to bugs
- JPA Criteria
    - Lot's of overhead and code that can lead to bugs
    - Tough to test properly
- RSQL
    - Lot's of overhead and code that can lead to bugs
    - Tough for the consumers to write RSQL queries
- Custom specifications
    - Lot's of overhead and code that can lead to bugs
    - Tough to test properly
### Reports in the database
This was created to provide robustnes after a restart of the webserver and to
provide the best possible information in the report