CREATE TABLE FOO
( ID                    INTEGER        GENERATED BY DEFAULT AS IDENTITY
, DATA                  VARCHAR (255)  NOT NULL
, CONSTRAINT FOO_PK     PRIMARY KEY (ID)
);

CREATE TABLE BAR
( ID                    INTEGER        GENERATED BY DEFAULT AS IDENTITY
, FOO_ID                INTEGER        NOT NULL
, DATA                  VARCHAR (255)  NOT NULL
, CONSTRAINT BAR_PK     PRIMARY KEY (ID)
, CONSTRAINT BAR_FOO_FK FOREIGN KEY (FOO_ID) REFERENCES FOO (ID)
);