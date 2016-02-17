--SQL statement that return the list 
--of all available news (news id, news title columns) 
--and one more column that display all concatenated tags 
--values, available for current news as a single string by using Oracle 10g DB features.


SELECT NEWS.ID,
  NEWS.TITLE,
  CAST(RTRIM(Sys_xmlagg(XMLELEMENT(col, TAG
  ||', ')).extract('//text()'),',') AS VARCHAR2(4000)) AS LIST_TAGS
FROM NEWS
INNER JOIN NEWS_TAG
ON NEWS_TAG.NEWS_ID = NEWS.ID
WHERE NEWS.ID       = NEWS.ID
GROUP BY NEWS.ID,
  NEWS.TITLE
ORDER BY NEWS.ID DESC;