-- NÃO EXECUTAR O ARQUIVO INTEIRO --

USE marketplace

/* Observação
não pode usar "USE" ou "GO" nas procedures T^T
*/

-- Execurar Procedures (stored procedure)
EXEC ResetarDadosCliente
EXEC ResetarDadosLoja

SELECT * FROM address

SELECT u.user_name, u.email, u.telephone, c.sex, c.social_name,
       a.address_name, a.cep, a.number, a.complement
FROM user_tbl u, client c, address a
WHERE u.user_name = c.user_name
    AND u.user_name = a.user_name

SELECT u.user_name, store_name, a.*
FROM user_tbl u, store s, store_address a
WHERE u.user_name = s.user_name
    AND a.user_name = u.user_name

-- Remoção das Procedures
DROP PROCEDURE ResetarDadosCliente


-- Criação das Procedures
/**  Criar a stored procedure para excluir e inserir registros  **/
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
    VALUES ('d', 'casa2', '89012345', 'SP', 'são paulo', 'jardim felicidade', 'maracuja', '1234',
            'ao lado da casa do salgado');
    INSERT INTO address
    VALUES ('d', 'casa3', '56789012', 'MG', 'belo horizonte', 'avenida tal', 'pinho verde', '456',
            'muro verde');
    INSERT INTO address
    VALUES ('d', 'trabalho', '23456789', 'MG', 'belo horizonte', 'avenida tal', 'pinho verde', '4567',
            'muro verde');
    INSERT INTO address
    VALUES ('d', 'trabalho2', '90123456', 'MG', 'belo horizonte', 'avenida tal', 'pinho verde', '321',
            'muro verde');
    INSERT INTO address
    VALUES ('d', 'galpao', '67890123', 'MG', 'belo horizonte', 'avenida tal', 'pinho verde', '4321',
            'muro verde');

    SELECT * FROM address
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

    SELECT u.user_name, store_name, a.*
    FROM user_tbl u, store s, store_address a
    WHERE u.user_name = s.user_name
        AND a.user_name = u.user_name
END
