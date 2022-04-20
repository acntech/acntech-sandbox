# Spring Boot OIDC Client Stateless

This is an OIDC protected web application with stateless session handling.

It is based on the [spring-boot-oidc-client-login](../oidc-client-login) example but with
session creation policy set to STATELESS. This means that Spring Security is configured
to not store authentication or authorization data in an HTTP session. In this example it 
will instead save the date in simple in-memory data stores.

## Features
* OIDC Client Login
* Stateless Session Creation Policy
* OAuth2 Authorization Request stored in an HTTP cookie instead of in an HTTP session
* Spring Security Context stored in-memory in the JVM instead of in an HTTP session
