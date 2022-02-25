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
## Testing
There is a swagger located in this repository as an postman collection used for testing
## Deploying to production
Adjust the datasource and authorization server properties using enviroment variables   
Use `mvnw springBoot:install` to build a `war` located in
`target\library-{version}.war` and use it to be deployed to your production
server, be that Tomcat or other methods
## Preformance
The tool for stres testing was [wrk](https://github.com/wg/wrk)  
**Getting authors**
```
Running 30s test @ http://localhost:8080/v1/authors
  3 threads and 400 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    22.30ms   22.70ms 590.60ms   97.47%
    Req/Sec     6.57k   689.98    10.85k    86.97%
  582801 requests in 30.05s, 718.76MB read
Requests/sec:  19391.40
Transfer/sec:     23.92MB
```
**Getting books**
Running 30s test @ http://localhost:8080/v1/books
  3 threads and 400 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    21.41ms   13.87ms 388.77ms   95.13%
    Req/Sec     6.47k   584.75     9.78k    85.78%
  579676 requests in 30.03s, 655.20MB read
Requests/sec:  19300.51
Transfer/sec:     21.81MB

## Decision
### schema.sql & data.sql
I could use the JPA solution for auto ddl generation but I chose the sql
variant to show my SQL understanding skill.
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