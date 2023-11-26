USE marketplace


select * from store_address where user_name = 'l'

delete store_address where user_name = 'l'
go
delete store where user_name = 'l'
go
delete user_tbl where user_name = 'l'

select *
from user_tbl

select *
from store

select *
from store_address

SELECT u.user_name AS login, u.email, u.telephone,
       c.sex, c.social_name AS nome, c.cpf, CONVERT(CHAR(10), c.date_birth, 103) AS date_birth
FROM user_tbl u, client c
WHERE u.user_name = 'd' AND c.user_name = u.user_name

SELECT u.user_name AS login, u.email, u.telephone,
       s.store_name, s.cnpj
FROM user_tbl u, store s
WHERE u.user_name = 'l' AND s.user_name = u.user_name

SELECT u.user_name AS login, u.email, u.telephone,
       s.store_name, s.cnpj
FROM user_tbl u, store s
WHERE u.user_name = s.user_name AND s.user_name = u.user_name

/*
"""
SELECT u.user_name AS login, u.email, u.telephone,
       s.store_name, s.cnpj
FROM user_tbl u, store s
WHERE u.user_name = 'l' AND s.user_name = u.user_name
"""
*/

/*
"""
SELECT u.user_name AS login, u.email, u.telephone,
       c.sex, c.social_name AS nome, c.cpf, CONVERT(CHAR(10), c.date_birth, 103) AS date_birth
FROM user_tbl u, client c
WHERE u.user_name = 'd' AND c.user_name = 'd'
""";
 */