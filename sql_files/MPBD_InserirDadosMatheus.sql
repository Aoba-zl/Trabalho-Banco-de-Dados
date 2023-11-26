----------------------------------------------Insert Users-------------------------------------------test
INSERT INTO user_tbl VALUES ('matheus', '123456', '11941318918', 'matheus@hotmail.com', 'client');
INSERT INTO client VALUES ('matheus', 'rogerin', '51888485892', '2000-03-01', 'masculino');


INSERT INTO user_tbl VALUES ('francisco', '123456', '11941318918', 'francisco@hotmail.com', 'store');
INSERT INTO store VALUES ('francisco', 'pao de açucar', '10101010101010');

INSERT INTO user_tbl VALUES ('david', '123456', '11941318918', 'david@hotmail.com', 'store');
INSERT INTO store VALUES ('david', 'pao de açucar', '10101010101010');

INSERT INTO user_tbl VALUES ('a', 'a', '11941318918', 'a@hotmail.com', 'store'); --apenas para login de test
INSERT INTO store VALUES ('a', 'pao de açucar', '10101010101010');

INSERT INTO user_tbl VALUES ('b', 'b', '11941318918', 'b@hotmail.com', 'client'); --apenas para login de test
INSERT INTO client VALUES ('b', 'roger', '66666666666', '2000-04-01', 'feminino');
-----------------------------------------------------------------------------------------------------test
---------------------------------------------Insert Product------------------------------------------test
INSERT INTO product
VALUES 
('francisco', 'Goiaba', 1.50, 10, 2, 'Frutas', 'Uma fruta muito munita, chamado goiaba, muito melhor do que uma passagem de onibus.', 0),
('david', 'Passagem de onibus', 1.50, 10, 3, 'Ticket', 'Uma passagem de onibus muito munito, muito melhor do que comprar uma goiaba ne.', 0),
('francisco', 'seila', 2.50, 10, 5, 'Seilas', 'Sei la dos seilas.', 0),
('francisco', 'seila2', 5.50, 10, 6, 'Seilas', 'Sei la dos seilas que vem dos seilas.', 0),
('david', 'seila3', 10.50, 10, 9, 'Seilas', 'Sei la dos seilas que vem dos seilaaaas.', 0),
('david', 'seila4', 20.50, 10, 6, COALESCE(NULLIF('', ''), 'Sem categoria'), 'Sei la dos seilas que vem dos seilaaaaaaaas.', 0)

--COALESCE(expressão1, expressão2, expressão3... expressãoN) ela avalia da esquerda para a direita, 
--caso o primeiro valor da sequencia não seja nulo, ele retornara o primeiro valor e segue a adiante.

--NULLIF(expressão1, expressão2) caso o valor da expressão 1 seja igual a expressão 2, ela retornara null, 
--caso não, retornara a expressão 1.