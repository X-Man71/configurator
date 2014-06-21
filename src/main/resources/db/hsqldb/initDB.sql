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

CREATE TABLE IF NOT EXISTS events (
  id         		INTEGER IDENTITY PRIMARY KEY,
  name 		 		VARCHAR(30),
  date 				DATE NOT NULL
);
CREATE INDEX events_name ON events (name);