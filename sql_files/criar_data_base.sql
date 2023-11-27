-- CREATE DATABASE marketplace
-- GO
-- USE marketplace

-- Criar tabelas MarketingPlace
CREATE TABLE user_tbl
(
	user_name 			VARCHAR(60) 		NOT NULL,
	user_password		VARCHAR(60) 		NOT NULL,
	telephone 			VARCHAR(11) 		NOT NULL,
	email 				VARCHAR(100)	 	NOT NULL UNIQUE,
	permission 			VARCHAR(50) 		NOT NULL
	PRIMARY KEY (user_name)
)
GO
CREATE TABLE store
( 
	user_name 			VARCHAR(60) 		NOT NULL,
	store_name 			VARCHAR(100) 		NOT NULL,
	cnpj 				CHAR(14) 			NOT NULL
	PRIMARY KEY (user_name)
	FOREIGN KEY (user_name) REFERENCES user_tbl (user_name)
)
GO
CREATE TABLE store_address
(
	user_name	 		VARCHAR(60) 		NOT NULL,
	cep 				CHAR(8)	 			NOT NULL,
	state 				VARCHAR(50) 		NOT NULL,
	city 				VARCHAR(50) 		NOT NULL,
	neighborhood		VARCHAR(50) 		NOT NULL,
	street	 			VARCHAR(75) 		NOT NULL,
	number 				VARCHAR(10) 		NOT NULL,
	complement	 		VARCHAR(50) 		NULL
	PRIMARY KEY (user_name)
	FOREIGN KEY (user_name) REFERENCES store (user_name)
)
GO
CREATE TABLE client
(
	user_name 			VARCHAR(60) 		NOT NULL,
	social_name 		VARCHAR(100)		NOT NULL,
	cpf 				CHAR(11) 			NOT NULL,
	date_birth			DATE 				NOT NULL,
	sex 				VARCHAR(25) 		NOT NULL
	PRIMARY KEY (user_name)
	FOREIGN KEY (user_name) REFERENCES user_tbl (user_name)
)
GO
CREATE TABLE address
(
	user_name 			VARCHAR(60) 		NOT NULL,
	address_name 		VARCHAR(50) 		NOT NULL,
	cep 				CHAR(8) 			NOT NULL,
	state 				VARCHAR(50) 		NOT NULL,
	city 				VARCHAR(50) 		NOT NULL,
	neighborhood		VARCHAR(50) 		NOT NULL,
	street	 			VARCHAR(60) 		NOT NULL,
	number	 			VARCHAR(10) 		NOT NULL,
	complement	 		VARCHAR(50) 		NULL
	PRIMARY KEY (user_name, cep, number)
	FOREIGN KEY (user_name) REFERENCES client (user_name)
)
GO
CREATE TABLE product
(
	id_product 			INT IDENTITY(1, 1)	NOT NULL,
	user_name 			VARCHAR(60) 		NOT NULL,
	name_product 		VARCHAR(100) 		NOT NULL,
	unity_price 		DECIMAL(8, 2) 		NOT NULL,
	total_stock 		INT 				NOT NULL,
	shipping	 		DECIMAL(8, 2) 		NOT NULL,
	category			VARCHAR(30)			NOT NULL,
	description			VARCHAR(255) 		NOT NULL,
	status				BIT					NOT NULL
	PRIMARY KEY (id_product)
	FOREIGN KEY (user_name) REFERENCES store (user_name)
)
GO
CREATE TABLE order_tbl
(
    id_order             INT IDENTITY(1, 1) NOT NULL,
    user_name_client     VARCHAR(60)        NOT NULL
    PRIMARY KEY (id_order)
    FOREIGN KEY (user_name_client) REFERENCES client (user_name)
)
GO
CREATE TABLE cart
(
    user_name           VARCHAR(60)         NOT NULL,
    id_order            INT                 NOT NULL,
    total               DECIMAL(10, 2)      NOT NULL
    PRIMARY KEY (user_name)
    FOREIGN KEY (user_name) REFERENCES client (user_name),
    FOREIGN KEY (id_order) REFERENCES order_tbl (id_order)
)
GO
CREATE TABLE order_product
(
	id_order 			INT 	 			NOT NULL,
	id_product 			INT 				NOT NULL,
	quantity			INT 				NOT NULL,
	sub_total 			DECIMAL(10, 2)
	PRIMARY KEY (id_order, id_product)
	FOREIGN KEY (id_order) REFERENCES order_tbl (id_order),
	FOREIGN KEY (id_product) REFERENCES product(id_product)
)
GO
CREATE TABLE payment
(
	id_order 			INT 				NOT NULL,
	total_pay	 		DECIMAL(10, 2) 		NOT NULL,
	date_pay 			DATE 				NOT NULL,
	status 				VARCHAR(50) 		NOT NULL
	PRIMARY KEY (id_order)
	FOREIGN KEY (id_order) REFERENCES order_tbl (id_order)
)
GO
CREATE TABLE pix
(
	id_order 			INT 				NOT NULL,
	qr_code 			VARCHAR(100) 		NOT NULL
	PRIMARY KEY (id_order)
	FOREIGN KEY (id_order) REFERENCES payment (id_order)
)
GO
CREATE TABLE payment_slip
(
	id_order 			INT 				NOT NULL,
	qr_code 			VARCHAR(50) 		NOT NULL
	PRIMARY KEY (id_order)
	FOREIGN KEY (id_order) REFERENCES payment (id_order)
)