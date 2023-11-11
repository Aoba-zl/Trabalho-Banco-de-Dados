-- NÃO EXECUTAR O ARQUIVO INTEIRO --

USE marketplace

/* Observação
não pode usar "USE" ou "GO" nas procedures T^T
*/

-- Execurar Procedures (stored procedure)
EXEC ResetarDadosCliente

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


