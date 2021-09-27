CREATE TABLE QUERIES
( ID                            INTEGER        GENERATED ALWAYS AS IDENTITY
, NAME                          VARCHAR (255)  NOT NULL
, QUERY                         CLOB           NOT NULL
, CONSTRAINT QUERIES_PK         PRIMARY KEY (ID)
, CONSTRAINT QUERIES_NAME_UC    UNIQUE (NAME)
);