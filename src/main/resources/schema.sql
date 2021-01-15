CREATE TABLE IF NOT EXISTS userData (
	userId INT PRIMARY KEY AUTO_INCREMENT,
	userName VARCHAR(20) UNIQUE,
	mailAddress VARCHAR(100),
	password VARCHAR(100),
	role VARCHAR(20),
	unavailableFlag INT default 0
);

CREATE TABLE IF NOT EXISTS guest (
	guestId INT PRIMARY KEY AUTO_INCREMENT,
	guestName VARCHAR(20),
	unavailableFlag INT default 0
);

CREATE TABLE IF NOT EXISTS gift (
  giftId INT PRIMARY KEY AUTO_INCREMENT,
  guestId INT,
  giftName VARCHAR(50),
  price VARCHAR(100),
  image VARCHAR(20),
  description VARCHAR(3000),
  shop VARCHAR(100),
  address VARCHAR(500),
  phone VARCHAR(50),
  unavailableFlag INT default 0,
  CONSTRAINT fk_gift_guest FOREIGN KEY fk_guestId(guestId) REFERENCES guest (guestId)
);


CREATE TABLE IF NOT EXISTS favGift (
  favId INT PRIMARY KEY AUTO_INCREMENT,
  userId VARCHAR(50),
  giftId INT,
  unavailableFlag INT default 0,
  CONSTRAINT fk_fav_user FOREIGN KEY fk_userId(userId) REFERENCES guest (guestId)
  CONSTRAINT fk_fav_gift FOREIGN KEY fk_giftId(giftId) REFERENCES gift (giftId)
);