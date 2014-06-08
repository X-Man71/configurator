DROP TABLE users IF EXISTS;
DROP TABLE authorities IF EXISTS;
DROP TABLE user_authorities IF EXISTS;

CREATE TABLE IF NOT EXISTS users (
 	 id INTEGER IDENTITY PRIMARY KEY,
 	 firstName 		VARCHAR(10) NOT NULL,  
     lastName 		VARCHAR(60) NOT NULL,
     username 		VARCHAR(10) NOT NULL,  
     password 		VARCHAR(60) NOT NULL,
     email 			VARCHAR(60) NOT NULL,
	 consumerRate INTEGER,
	 consumerRateValideUntil DATE
);  

CREATE TABLE IF NOT EXISTS user_authority (
	user_id 		INTEGER NOT NULL,
	authority_id 	INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS authority (
	id 				INTEGER IDENTITY PRIMARY KEY,
	name 			VARCHAR(60) NOT NULL
);


CREATE TABLE IF NOT EXISTS loans (
  requestId     	INTEGER IDENTITY PRIMARY KEY,
  status 			VARCHAR(30),
  amount 			DOUBLE,
  term				INTEGER,
  quoteRate			DOUBLE NOT NULL,
  responseTime		DATE,
  user_id			INTEGER
);
