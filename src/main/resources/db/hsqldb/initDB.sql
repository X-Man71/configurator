DROP TABLE users IF EXISTS;
DROP TABLE authorities IF EXISTS;
DROP TABLE user_authorities IF EXISTS;
DROP TABLE events IF EXISTS;
DROP TABLE locations IF EXISTS;

CREATE TABLE IF NOT EXISTS users (
 	 id INTEGER IDENTITY PRIMARY KEY,
 	 firstName 		VARCHAR(10) NOT NULL,  
     lastName 		VARCHAR(60) NOT NULL,
     username 		VARCHAR(10) NOT NULL,  
     password 		VARCHAR(60) NOT NULL,
     email 			VARCHAR(60) NOT NULL,
	 corporation 	VARCHAR(60) NOT NULL,
	 gender 		VARCHAR(60) NOT NULL,
	 residence 		VARCHAR(60) NOT NULL,
	 street 		VARCHAR(60) NOT NULL,
	 zipcode 		VARCHAR(60) NOT NULL,
	 phone 			VARCHAR(60) NOT NULL
);  

CREATE TABLE IF NOT EXISTS user_authority (
	user_id 		INTEGER NOT NULL,
	authority_id 	INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS authority (
	id 				INTEGER IDENTITY PRIMARY KEY,
	name 			VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS banks (
  id         		INTEGER IDENTITY PRIMARY KEY,
  name 		 		VARCHAR(30),
  numberOfLoans 	INTEGER NOT NULL,
);
CREATE INDEX banks_name ON banks (name);

CREATE TABLE IF NOT EXISTS loans (
  id         		INTEGER IDENTITY PRIMARY KEY,
  name       		VARCHAR(30) NOT NULL,
  status 			TINYINT NOT NULL,
  rate 				DOUBLE NOT NULL,
  requestno 		INTEGER NOT NULL,
  quoteno 			INTEGER NOT NULL,
  bank_id   		INTEGER NOT NULL
);
ALTER TABLE loans ADD CONSTRAINT fk_loans_banks FOREIGN KEY (bank_id) REFERENCES banks (id);
CREATE INDEX loans_name ON loans (name);