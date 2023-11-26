---------------------------------------------Consult Users-------------------------------------------
SELECT * FROM user_tbl

SELECT * FROM client

SELECT * FROM user_tbl ut, client c 
WHERE ut.user_name = c.user_name 

SELECT * FROM store

SELECT * FROM user_tbl ut, store s
WHERE ut.user_name = s.user_name 

SELECT * FROM product 

SELECT user_name FROM user_tbl WHERE user_name = 'francisco' AND user_password = '123456'

SELECT u.user_name, u.user_password, u.telephone, u.email, u.permission, 
       c.social_name, c.cpf, c.date_birth, c.sex
FROM user_tbl AS u
INNER JOIN client AS c ON u.user_name = c.user_name

SELECT *
FROM user_tbl ut 

SELECT u.user_name, u.user_password, u.telephone, u.email, u.permission, 
       c.social_name, c.cpf, c.date_birth, c.sex
FROM user_tbl u, client c
WHERE u.user_name = c.user_name 

SELECT u.user_name, u.user_password, u.telephone, u.email, u.permission, 
       s.store_name, s.cnpj
FROM user_tbl AS u
INNER JOIN store AS s ON u.user_name = s.user_name

SELECT *
FROM user_tbl AS u
INNER JOIN client AS c ON u.user_name = c.user_name
-----------------------------------------------------------------------------------------------------
------------------------------------------Consult Products-------------------------------------------
SELECT * FROM product;

SELECT * FROM product 
ORDER BY id_product 
OFFSET 0 ROWS 
FETCH NEXT 10 ROWS ONLY;

SELECT name_product, unity_price, total_stock, category, description FROM product

SELECT name_product, unity_price, total_stock, category, description FROM product

SELECT id_product, name_product, unity_price, description FROM product