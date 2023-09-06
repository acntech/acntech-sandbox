# Spring Boot OAuth2 Client Stateless

This is an OAuth2 protected web application with stateless session handling.

It is based on the [spring-boot-oauth2-client-login](../oauth2-client-login) example but with
session creation policy set to STATELESS. This means that Spring Security is configured
to not store authentication or authorization data in an HTTP session. In this example it 
will instead save the date in simple in-memory data stores.

## Features
* OAuth2 Client Login
* Stateless Session Creation Policy
* OAuth2 Authorization Request stored in an HTTP cookie instead of in an HTTP session
* Spring Security Context stored in-memory in the JVM instead of in an HTTP session
