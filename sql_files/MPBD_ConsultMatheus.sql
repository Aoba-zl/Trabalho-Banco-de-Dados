---------------------------------------------Consult Users-------------------------------------------
SELECT * FROM user_tbl

SELECT * FROM client

SELECT * FROM user_tbl

SELECT * FROM store

SELECT user_name FROM user_tbl WHERE user_name = 'francisco' AND user_password = '123456'

SELECT u.user_name AS user_name, u.user_password, u.telephone, u.email, u.permission, 
       c.social_name, c.cpf, c.date_birth, c.sex
FROM user_tbl AS u
INNER JOIN client AS c ON u.user_name = c.user_name

SELECT u.user_name AS user_name, u.user_password, u.telephone, u.email, u.permission, 
       s.store_name, s.cnpj
FROM user_tbl AS u
INNER JOIN store AS s ON u.user_name = s.user_name

SELECT *
FROM user_tbl AS u
INNER JOIN client AS c ON u.user_name = c.user_name
-----------------------------------------------------------------------------------------------------
------------------------------------------Consult Products-------------------------------------------
SELECT * FROM product;

SELECT name_product, unity_price, total_stock, category, description FROM product

SELECT name_product, unity_price, total_stock, category, description FROM product

SELECT id_product, name_product, unity_price, description FROM product