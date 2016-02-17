--SQL statement that return the list 
--of all available news (news id, news title columns) 
--and one more column that display all concatenated tags 
--values, available for current news as a single string.

SELECT NEWS.ID,
  getlisttags(NEWS.ID, '; ') AS LIST_TAGS,
  NEWS.TITLE
FROM NEWS
GROUP BY NEWS.ID,
  NEWS.TITLE
ORDER BY ID DESC;