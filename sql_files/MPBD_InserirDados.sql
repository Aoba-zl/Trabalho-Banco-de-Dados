/*
USE master
*/

USE marketplace
GO

-- Inserir Dados

/*  PlaceHolders de Acesso rápido  */
-- placeHolder Cliente
INSERT INTO user_tbl VALUES ('c', 'c', '11941318918', 'debora@email.com', 'client');
INSERT INTO client VALUES ('c', 'Cliente', '12345678910', '2000-04-01', 'feminino');
INSERT INTO address VALUES ('c', 'casa1', '12345678', 'SP', 'são paulo', 'jardim helena', 'goiabeira', '512', NULL)

GO

INSERT INTO user_tbl VALUES ('a', 'a', '11941318918', 'debora@email.com', 'client');
INSERT INTO client VALUES ('a', 'Cliente', '12345678910', '2000-04-01', 'masculino');
INSERT INTO address VALUES ('a', 'casa1', '12345678', 'SP', 'são paulo', 'jardim helena', 'goiabeira', '512', NULL)


INSERT INTO user_tbl VALUES ('b', 'b', '11941318918', 'debora@email.com', 'client');
INSERT INTO client VALUES ('b', 'Cliente', '12345678910', '2000-04-01', 'neutro');
INSERT INTO address VALUES ('b', 'casa1', '12345678', 'SP', 'são paulo', 'jardim helena', 'goiabeira', '512', NULL)


-- PlaceHolder Loja
INSERT INTO user_tbl VALUES ('l', 'l', '11941318918', 'francisco@outlook.com', 'store');
INSERT INTO store VALUES ('l', 'Loja', '12349876000159')
INSERT INTO store_address VALUES ('l', '08059905', 'SP', 'guarulhos', 'larangeira', 'alberto funalo', '503625', 'Faz o L!')


/*  Clientes  */
-- cliente 1
INSERT INTO user_tbl VALUES ('debby123', '123', '11941318918', 'debora@email.com', 'client');
INSERT INTO client VALUES ('debby123', 'debora cristina', '12345678910', '2000-04-01', 'feminino');
-- endereços
INSERT INTO address VALUES ('debby123', 'casa1', '12345678', 'SP', 'são paulo', 'jardim helena', 'goiabeira', '512', NULL)
INSERT INTO address VALUES ('debby123', 'casa2', '12345678', 'SP', 'são paulo', 'jardim felicidade', 'maracuja', '1025', 'ao lado da casa do salgado')
INSERT INTO address VALUES ('debby123', 'trabalho', '12349978', 'MG', 'belo horizonte', 'avenida tal', 'pinho verde', '1221', 'muro verde')

GO

-- cliente 2
INSERT INTO user_tbl VALUES ('nickName', '888@abc', '11941318818', 'nick@email.com', 'client');
INSERT INTO client VALUES ('nickName', 'Roger dos Santos', '51888485892', '1999-03-01', 'masculino');
-- endereços
INSERT INTO address VALUES ('nickName', 'minha casa', '08059905', 'SP', 'guarulhos', 'larangeira', 'alberto funalo', '503624', NULL)

/*  Lojas  */
-- loja 1
INSERT INTO user_tbl VALUES ('francisco', '123456', '11941317718', 'frano@email.com', 'store');
INSERT INTO store VALUES ('francisco', 'pao de açucar', '12345678000112');
-- endereço
INSERT INTO store_address VALUES ('francisco', '08059905', 'SP', 'guarulhos', 'larangeira', 'alberto funalo', '503624', NULL)

GO

-- loja 2
INSERT INTO user_tbl VALUES ('francisco2', '1234', '11941318918', 'francisco@outlook.com', 'store');
INSERT INTO store VALUES ('francisco2', 'padaria', '12349876000159');
-- endereço
INSERT INTO store_address VALUES ('francisco2', '08059905', 'SP', 'guarulhos', 'larangeira', 'alberto funalo', '503625', 'do lado do pão de açucar')

/* Conferir
SELECT user_name, user_password FROM user_tbl
SELECT * FROM user_tbl

SELECT * FROM client

SELECT * FROM address

SELECT * FROM store

SELECT * FROM store_address

SELECT CONVERT(CHAR(10), date_birth, 103) AS date_birth FROM client
*/
