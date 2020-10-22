CREATE TABLE IF NOT EXISTS omiyage (
  omiyaID INT PRIMARY KEY,
  guest VARCHAR(50),
  name VARCHAR(100),
  price VARCHAR(500),
  image VARCHAR(50),
  description VARCHAR(3000),
  shop VARCHAR(100),
  address VARCHAR(500),
  phone VARCHAR(50),
  keyword VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS userData (
	userId VARCHAR(50) PRIMARY KEY,
	mailAddress VARCHAR(100),
	password VARCHAR(100),
	role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS FavOmiyage (
  favId VARCHAR(50) PRIMARY KEY,
  userId VARCHAR(50),
  omiyaID INT,
  guest VARCHAR(50),
  name VARCHAR(100),
  price VARCHAR(500),
  image VARCHAR(50),
  description VARCHAR(3000),
  shop VARCHAR(100),
  address VARCHAR(500),
  phone VARCHAR(50)
);