--custom db function that return the list of all tags referred 
--to a current news, concatenated by specified separator character. 
--Function accept the news id and separator character as input parameters 
--and return a single string as a result of tag values concatenation.

CREATE OR REPLACE FUNCTION GetListTags(
    NEWS      IN NUMBER,
    SEPARATOR IN VARCHAR2)
  RETURN CLOB
IS
  list_tags CLOB;
BEGIN
  FOR existed_tag IN
  (SELECT TAG FROM NEWS_TAG WHERE NEWS_TAG.NEWS_ID = NEWS
  )
  LOOP
    list_tags := list_tags || existed_tag.TAG || SEPARATOR ;
  END LOOP;
RETURN list_tags;
END GetListTags;