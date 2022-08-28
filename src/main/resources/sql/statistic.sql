SELECT COUNT(*)                                AS count,
       SUM(IF(r.status_id = '1', 1, 0))  AS processing_count,
       SUM(IF(r.status_id = '2', 1, 0))    AS approved_count,
       SUM(IF(r.status_id = '3', 1, 0)) AS disapproved_count
FROM report as r;

SELECT year, COUNT(*) as count
FROM report AS r
group by r.year
order by year;

SELECT SUM(IF(u.role_id = '1', 1, 0))      AS user_count,
       SUM(IF(u.role_id = '2', 1, 0)) AS inspector_count
FROM user as u;

INSERT INTO tax_data.user (role_id, entrepreneur_type_id, name, lastname, email, password, registration_time, tin, address) VALUES (2, 1, 'Admin', 'Admin', 'a@a.a', '123456', DEFAULT, 0000000000, 'Home');