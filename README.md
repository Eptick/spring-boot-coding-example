## Dependencies
postgresql 13+
## Running the application

Run this command from the terminal in the source folder

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
#### Windows
```
mvnw spring-boot:run
```
## *nix systems
```
./mvnw spring-boot:run
```