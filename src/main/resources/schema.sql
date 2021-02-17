CREATE TABLE IF NOT EXISTS userData (
	userId SERIAL PRIMARY KEY,
	userName VARCHAR(20) UNIQUE,
	mailAddress VARCHAR(100),
	password VARCHAR(100),
	role VARCHAR(20),
	unavailableFlag INT
);

CREATE TABLE IF NOT EXISTS guest (
	guestId SERIAL PRIMARY KEY,
	guestName VARCHAR(30),
	unavailableFlag INT default 0
);

CREATE TABLE IF NOT EXISTS gift (
  	giftId SERIAL PRIMARY KEY,
  	guestId INT,
  	giftName VARCHAR(50),
  	price VARCHAR(100),
  	image VARCHAR(20),
  	description VARCHAR(3000),
  	shop VARCHAR(100),
  	address VARCHAR(500),
  	phone VARCHAR(50),
  	unavailableFlag INT,
  	FOREIGN KEY (guestId) REFERENCES guest(guestId)
);


CREATE TABLE IF NOT EXISTS favGift (
  	favId SERIAL PRIMARY KEY,
  	userId INT,
  	giftId INT,
  	unavailableFlag INT
);