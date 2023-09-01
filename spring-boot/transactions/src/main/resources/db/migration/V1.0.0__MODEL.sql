CREATE TABLE NAMES
(
    ID   INTEGER GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(255) NOT NULL,
    CONSTRAINT NAME_PK PRIMARY KEY (ID)
);

CREATE TABLE PREFIXES
(
    ID      INTEGER GENERATED ALWAYS AS IDENTITY,
    NAME_ID INTEGER      NOT NULL,
    PREFIX  VARCHAR(255) NOT NULL,
    CONSTRAINT PREFIX_PK PRIMARY KEY (ID),
    CONSTRAINT PREFIX_NAME_FK FOREIGN KEY (NAME_ID) REFERENCES NAMES (ID)
);