DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS tariff;
DROP TABLE IF EXISTS sale;
DROP TABLE IF EXISTS discount;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS taxi_order;


CREATE TABLE address
(
    id        VARCHAR(20) NOT NULL,
    address   VARCHAR(50) NULL,
    latitude  DOUBLE      NULL,
    longitude DOUBLE      NULL,
    PRIMARY KEY (id)
);

CREATE TABLE car
(
    id            VARCHAR(20) NOT NULL,
    description   VARCHAR(50) NULL,
    car_number    VARCHAR(8)  NOT NULL,
    driver_number VARCHAR(13) NULL,
    seat          INT         NULL,
    type          INT         NULL,
    availability  INT         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tariff
(
    id          VARCHAR(10) NOT NULL,
    tariff_name VARCHAR(20) NULL,
    price       INTEGER     NULL,
    PRIMARY KEY (id)
);

CREATE TABLE sale
(
    id        VARCHAR(10) NOT NULL,
    sale_name VARCHAR(20) NULL,
    amount    DOUBLE      NULL,
    PRIMARY KEY (id)
);

CREATE TABLE discount
(
    id      VARCHAR(10) NOT NULL,
    user_id VARCHAR(20) NULL,
    percent DOUBLE      NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user
(
    id           VARCHAR(20) NOT NULL,
    email        VARCHAR(64) NOT NULL,
    password     VARCHAR(20) NOT NULL,
    name         VARCHAR(20) NULL,
    surname      VARCHAR(20) NULL,
    phone_number VARCHAR(13) NULL,
    birthday     DATE        NULL,
    gender       VARCHAR(10) NULL,
    role         INT         NULL,
    PRIMARY KEY (id)
);

CREATE TABLE taxi_order
(
    id              VARCHAR(20) NOT NULL,
    id_user         VARCHAR(20) NOT NULL,
    id_car          VARCHAR(20) NOT NULL,
    id_sale         VARCHAR(10) NULL,
    id_discount     VARCHAR(10) NULL,
    order_date      DATETIME    NULL,
    id_address_from VARCHAR(20) NULL,
    id_address_to   VARCHAR(20) NULL,
    id_tariff       VARCHAR(10) NULL,
    type            INT         NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_user) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_car) REFERENCES car (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_sale) REFERENCES sale (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_discount) REFERENCES discount (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_address_from) REFERENCES address (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_address_to) REFERENCES address (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_tariff) REFERENCES tariff (id) ON UPDATE CASCADE ON DELETE CASCADE
);



