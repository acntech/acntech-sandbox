# Spring Boot OAuth2 Client Stateless Token Cache

This is an OAuth2 protected web application with stateless session handling.

It is based on the [spring-boot-oauth2-client-login-stateless](../oauth2-client-login-stateless) example
but with OAuth2 token being stored in an external [Redis cache](https://redis.io) instead of in-memory in
the Java runtime.

## Features
* OAuth2 Client Login
* Stateless Session Creation Policy
* OAuth2 Authorization Request stored in an HTTP cookie instead of in an HTTP session
* OAuth2 token stored in a Redis cache instead of in an HTTP session
