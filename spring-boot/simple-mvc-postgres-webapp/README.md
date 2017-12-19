# Simple Spring Boot MVC Postgres WebApp

This is a simple Spring MVC webapp with a PostgreSQL database backend.

To run:
* Build Java code with Maven:
```
mvn clean package
```
* Build Docker image containing the webapp:
```
docker-compose build
```
* Run webapp and database Docker containers:
```
docker-compose up
```

Navigate to webapp in browser:
* [http://localhost:8080](http://localhost:8080)
