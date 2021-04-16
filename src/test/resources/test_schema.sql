TRUNCATE TABLE favorites RESTART IDENTITY CASCADE;
TRUNCATE TABLE gifts RESTART IDENTITY CASCADE;
TRUNCATE TABLE recommenders RESTART IDENTITY CASCADE;
TRUNCATE TABLE users RESTART IDENTITY CASCADE;

CREATE TABLE IF NOT EXISTS users (
	userId SERIAL PRIMARY KEY,
	userName VARCHAR(20) UNIQUE,
	mailAddress VARCHAR(100),
	password VARCHAR(100),
	role VARCHAR(20),
	isEnabled boolean default true
);

CREATE TABLE IF NOT EXISTS recommenders (
	recommenderId SERIAL PRIMARY KEY,
	recommenderName VARCHAR(30),
	isEnabled boolean default true
);

CREATE TABLE IF NOT EXISTS gifts (
  	giftId SERIAL PRIMARY KEY,
  	recommenderId INT,
  	giftName VARCHAR(50),
  	price VARCHAR(100),
  	image VARCHAR(20),
  	description VARCHAR(3000),
  	shop VARCHAR(100),
  	address VARCHAR(500),
  	phone VARCHAR(50),
  	isEnabled boolean default true,
  	CONSTRAINT FK_recommenderId FOREIGN KEY (recommenderId) REFERENCES recommenders(recommenderId)
);


CREATE TABLE IF NOT EXISTS favorites (
  	favoriteId SERIAL PRIMARY KEY,
  	userId INT,
  	giftId INT,
  	CONSTRAINT FK_userId FOREIGN KEY (userId) REFERENCES users(userId),
  	CONSTRAINT FK_giftId FOREIGN KEY (giftId) REFERENCES gifts(giftId)
);

ALTER SEQUENCE recommenders_recommenderId_seq RESTART WITH 1000;
ALTER SEQUENCE gifts_giftId_seq RESTART WITH 1000;
