## Dependencies
postgresql 13+
## Running the application

Run this command from the terminal in the source folder

## Retrieving the token
```
curl http://localhost:8888/token?scope=admin
curl http://localhost:8888/token?scope=author
```
#### Windows
```
mvnw spring-boot:run
```
## *nix systems
```
./mvnw spring-boot:run
```