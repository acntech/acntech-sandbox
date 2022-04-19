CREATE USER sandbox WITH PASSWORD 'abcd1234';
CREATE USER sandbox_workflow WITH PASSWORD 'abcd1234';
CREATE USER sandbox_uaa WITH PASSWORD 'abcd1234';
CREATE USER sandbox_keycloak WITH PASSWORD 'abcd1234';

CREATE DATABASE sandbox WITH OWNER sandbox;
CREATE DATABASE sandbox_workflow WITH OWNER sandbox_workflow;
CREATE DATABASE sandbox_uaa WITH OWNER sandbox_uaa;
CREATE DATABASE sandbox_keycloak WITH OWNER sandbox_keycloak;
