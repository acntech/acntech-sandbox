INSERT INTO GREETINGS (MESSAGE) SELECT 'This is an initial greeting' WHERE NOT EXISTS (SELECT 1 FROM GREETINGS WHERE MESSAGE = 'This is an initial greeting');