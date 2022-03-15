# Spring Boot Camunda Dedicated Datasource

This example shows how to configure a dedicated datasource for the Camunda engine.
Spring Boot will have its own datasource which will be used by Flyway and JPA.

To start this application you must first start a Docker container with Postgres.
Go to the /docker/postgres folder and start the container using `docker-compose up -d`.
There will be two databases created in Postgres, one named `sandbox` and one named `sandbox_workflow`.
Spring JPA and Flyway will use `sandbox` while Camunda will use `sandbox_workflow`.
Camunda is configured to update the schema which means it will create or update all tables needed
by the Camunda Engine.