CREATE TABLE FOO
( ID                    INTEGER        GENERATED BY DEFAULT AS IDENTITY
, BAR                   VARCHAR (255)  NOT NULL
, CONSTRAINT PERSON_PK  PRIMARY KEY (ID)
);