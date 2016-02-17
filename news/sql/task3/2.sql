--This SQL statement to select author names who wrote more than 3000 characters, 
--but the average number of characters per news 
--is more than 5.

SELECT AUTHORS.NAME
FROM NEWS_AUTHORS
LEFT JOIN AUTHORS
ON AUTHORS.ID=NEWS_AUTHORS.AUTHOR_ID
LEFT JOIN NEWS
ON NEWS_AUTHORS.NEWS_ID=NEWS.ID
GROUP BY AUTHORS.NAME
HAVING SUM(LENGTH(NEWS.FULL_TEXT) + LENGTH(NEWS.TITLE) + LENGTH(NEWS.DESCRIPTION)) < 3000
AND AVG(LENGTH(NEWS.FULL_TEXT)    + LENGTH(NEWS.TITLE) + LENGTH(NEWS.DESCRIPTION)) > 5;