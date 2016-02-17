--statement that generate 
--random authors distribution by competition pairs.
--Statement generate a list of author names pairs selected for a single 
--round of tournament, displayed as two separate columns. Each author presented in resulting 
--set (in both columns) once only. (The total number of authors even. 

WITH random_authors AS
  (SELECT name FROM  AUTHORS ORDER BY dbms_random.RANDOM)
SELECT NAME1,
  NAME2
FROM
  (SELECT name AS NAME1,
    ROWNUM AS rn1
  FROM
    (SELECT name, ROWNUM AS rn FROM random_authors
    )
  WHERE mod(rn, 2) = 0
  )res1
RIGHT JOIN
  (SELECT name AS NAME2,
    ROWNUM AS rn2
  FROM
    (SELECT name, ROWNUM AS rn FROM random_authors
    )
  WHERE mod(rn, 2) = 1
  )res2
ON res1.rn1 = res2.rn2;