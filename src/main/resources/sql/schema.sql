CREATE SCHEMA IF NOT EXISTS Taxi;


CREATE TABLE IF NOT EXISTS Address
(
	id                   VARCHAR(20) NOT NULL,
	departure            VARCHAR(30) NULL,
	arriving             VARCHAR(30) NULL,
	distance             DOUBLE NULL
);



ALTER TABLE Address
ADD PRIMARY KEY (id);



CREATE TABLE IF NOT EXISTS Car
(
	car_number           VARCHAR(8) NOT NULL,
	year_of_issue        YEAR NULL,
	model                VARCHAR(30) NULL,
	color                VARCHAR(20) NULL,
	availability         boolean NULL,
	make                 VARCHAR(20) NULL
);



ALTER TABLE Car
ADD PRIMARY KEY (car_number);



CREATE TABLE IF NOT EXISTS Order
(
	id                   VARCHAR(20) NOT NULL,
	order_status         VARCHAR(10) NULL,
	order_date           DATE NULL,
	id_user              VARCHAR(10) NULL,
	id_sale              VARCHAR(10) NULL,
	id_tariff            VARCHAR(10) NULL,
	car_number           VARCHAR(8) NULL,
	id_address           VARCHAR(20) NULL
);



ALTER TABLE Order
ADD PRIMARY KEY (id);



CREATE TABLE IF NOT EXISTS Special
(
	id                   VARCHAR(10) NOT NULL,
	sale_amount          DOUBLE NULL,
	sale_name            VARCHAR(20) NULL
);



ALTER TABLE Special
ADD PRIMARY KEY (id);



CREATE TABLE IF NOT EXISTS Tariff
(
	id                   VARCHAR(10) NOT NULL,
	price                INTEGER NULL
);



ALTER TABLE Tariff
ADD PRIMARY KEY (id);



CREATE TABLE IF NOT EXISTS User
(
	id                   VARCHAR(10) NOT NULL,
	email                VARCHAR(64) NULL,
	password             VARCHAR(20) NOT NULL,
	name                 VARCHAR(20) NULL,
	surname              VARCHAR(20) NULL,
	phone_number         VARCHAR(13) NULL
);



ALTER TABLE User
ADD PRIMARY KEY (id);



ALTER TABLE Order
ADD FOREIGN KEY R_11 (id_user) REFERENCES User (id);



ALTER TABLE Order
ADD FOREIGN KEY R_12 (id_sale) REFERENCES Special (id);



ALTER TABLE Order
ADD FOREIGN KEY R_13 (id_tariff) REFERENCES Tariff (id);



ALTER TABLE Order
ADD FOREIGN KEY R_14 (car_number) REFERENCES Car (car_number);



ALTER TABLE Order
ADD FOREIGN KEY R_15 (id_address) REFERENCES Address (id);




