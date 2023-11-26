-- NÃO EXECUTAR O ARQUIVO INTEIRO --

USE marketplace

/* Observação
não pode usar "USE" ou "GO" nas procedures T^T
*/

-- Execurar Procedures (stored procedure)

EXEC ResetarDadosUsuarios

EXEC ResetarDadosCliente
EXEC ResetarDadosClienteA
EXEC ResetarDadosClienteB
EXEC ResetarDadosLoja
EXEC ResetarDadosLojaA
EXEC ResetarDadosLojaB

SELECT * FROM client
SELECT * FROM store
SELECT * FROM address
SELECT * FROM store_address
SELECT * FROM user_tbl

SELECT u.user_name, u.user_password, u.permission
FROM user_tbl u

-- Remoção das Procedures
DROP PROCEDURE ResetarDadosCliente
DROP PROCEDURE ResetarDadosClienteA
DROP PROCEDURE ResetarDadosClienteB
DROP PROCEDURE ResetarDadosLoja
DROP PROCEDURE ResetarDadosLojaA
DROP PROCEDURE ResetarDadosLojaB

drop procedure ResetarDadosUsuarios

-- Criação das Procedures
/**  Criar a stored procedure para excluir e inserir registros  **/
CREATE PROCEDURE ResetarDadosUsuarios
AS
BEGIN

    delete address;
    delete client;
    delete store_address;
    delete store;
    delete user_tbl;

END

CREATE PROCEDURE ResetarDadosCliente
AS
BEGIN
    -- Excluir registros
    DELETE FROM address WHERE user_name = 'd';
    DELETE FROM client WHERE user_name = 'd';
    DELETE FROM user_tbl WHERE user_name = 'd';

    -- Inserir novos registros
    INSERT INTO user_tbl VALUES ('d', 'd', '11941318918', 'debora@email.com', 'client');
    INSERT INTO client VALUES ('d', 'debora cristina', '12345678910', '2000-04-01', 'feminino');

    -- Inserir novos endere�os
    INSERT INTO address
        VALUES ('d', 'casa1', '12345678', 'SP', 'são paulo', 'jardim helena', 'goiabeira', '123', NULL);
    INSERT INTO address
        VALUES ('d', 'casa2', '13245678', 'SP', 'são paulo', 'jardim felicidade', 'maracuja', '1234',
            'ao lado da casa do salgado');
    INSERT INTO address
        VALUES ('d', 'casa3', '14235678', 'MG', 'belo horizonte', 'avenida tal', 'pinho verde', '456',
            'muro verde');
END

CREATE PROCEDURE ResetarDadosClienteA
AS
BEGIN
    -- Excluir registros
    DELETE FROM address WHERE user_name = 'a';
    DELETE FROM client WHERE user_name = 'a';
    DELETE FROM user_tbl WHERE user_name = 'a';

    -- Inserir novos registros
    INSERT INTO user_tbl VALUES ('a', 'a', '11941318918', 'debora@email.com', 'client');
    INSERT INTO client VALUES ('a', 'debora cristina', '12345678910', '2000-04-01', 'feminino');

    -- Inserir novos endere�os
    INSERT INTO address
        VALUES ('a', 'casa1', '12345678', 'SP', 'são paulo', 'jardim helena', 'goiabeira', '123', NULL);
    INSERT INTO address
        VALUES ('a', 'casa2', '13245678', 'SP', 'são paulo', 'jardim felicidade', 'maracuja', '1234',
            'ao lado da casa do salgado');
    INSERT INTO address
        VALUES ('a', 'casa3', '14235678', 'MG', 'belo horizonte', 'avenida tal', 'pinho verde', '456',
            'muro verde');
END

CREATE PROCEDURE ResetarDadosClienteB
AS
BEGIN
    -- Excluir registros
    DELETE FROM address WHERE user_name = 'b';
    DELETE FROM client WHERE user_name = 'b';
    DELETE FROM user_tbl WHERE user_name = 'b';

    -- Inserir novos registros
    INSERT INTO user_tbl VALUES ('b', 'b', '11941318918', 'debora@email.com', 'client');
    INSERT INTO client VALUES ('b', 'debora cristina', '12345678910', '2000-04-01', 'feminino');

    -- Inserir novos endere�os
    INSERT INTO address
        VALUES ('b', 'casa1', '12345678', 'SP', N'são paulo', 'jardim helena', 'goiabeira', '123', NULL);
    INSERT INTO address
        VALUES ('b', 'casa2', '13245678', 'SP', 'são paulo', 'jardim felicidade', 'maracuja', '1234',
            'ao lado da casa do salgado');
    INSERT INTO address
        VALUES ('b', 'casa3', '14235678', 'MG', 'belo horizonte', 'avenida tal', 'pinho verde', '456',
            'muro verde');
END

CREATE PROCEDURE ResetarDadosLoja
AS
BEGIN
    DELETE store_address WHERE user_name = 'l'
    DELETE store WHERE user_name = 'l'
    DELETE user_tbl WHERE user_name = 'l'


    INSERT INTO user_tbl
    VALUES ('l', 'l', '11941318918', 'francisco@outlook.com', 'store');
    INSERT INTO store
    VALUES ('l', 'Loja', '12349876000159')
    INSERT INTO store_address
    VALUES ('l', '08059905', 'SP', 'guarulhos', 'larangeira', 'alberto funalo', '503625', 'Faz o L!')
END

CREATE PROCEDURE ResetarDadosLojaA
AS
BEGIN
    DELETE store_address WHERE user_name = 'm'
    DELETE store WHERE user_name = 'm'
    DELETE user_tbl WHERE user_name = 'm'


    INSERT INTO user_tbl
    VALUES ('m', 'm', '11941318918', 'francisco@outlook.com', 'store');
    INSERT INTO store
    VALUES ('m', 'Loja', '12349876000159')
    INSERT INTO store_address
    VALUES ('m', '08059905', 'SP', 'guarulhos', 'larangeira', 'alberto funalo', '503625', 'Faz o L!')
END

CREATE PROCEDURE ResetarDadosLojaB
AS
BEGIN
    DELETE store_address WHERE user_name = 'n'
    DELETE store WHERE user_name = 'n'
    DELETE user_tbl WHERE user_name = 'n'


    INSERT INTO user_tbl
    VALUES ('n', 'n', '11941318918', 'francisco@outlook.com', 'store');
    INSERT INTO store
    VALUES ('n', 'Loja', '12349876000159')
    INSERT INTO store_address
    VALUES ('n', '08059905', 'SP', 'guarulhos', 'larangeira', 'alberto funalo', '503625', 'Faz o L!')
END