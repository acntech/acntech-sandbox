CREATE TABLE GREETINGS
( ID                       INTEGER        GENERATED BY DEFAULT AS IDENTITY
, MESSAGE                  VARCHAR (255)  NOT NULL
, CONSTRAINT GREETINGS_PK  PRIMARY KEY (ID)
);