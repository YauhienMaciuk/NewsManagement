--Develop a query to calculate the number of news, written by each author, the average number of comments per news 
--for a current author and the most popular tag, referred to author news. All these information output in one single result set. 
--These query create based on a custom db view

CREATE OR REPLACE VIEW AUTHOR_STATS
AS
  SELECT AUTHOR_NAME,
    COUNT_NEWS,
    ROUND(AVG_COMMENTS_PER_NEWS, 5) AS AVG_COMMENTS_PER_NEWS,
    POPULAR_TAG
  FROM
    (SELECT AVG(AUTHOR_COMMENT) AVG_COMMENTS_PER_NEWS,
      COUNT(COUNT_NEWS) AS COUNT_NEWS,
      AUTHOR_ID         AS AU1
    FROM
      (SELECT COUNT(COMMENTS.ID) AUTHOR_COMMENT,
        COUNT(DISTINCT NEWS_AUTHORS.NEWS_ID) COUNT_NEWS,
        NEWS_AUTHORS.AUTHOR_ID
      FROM NEWS_AUTHORS
      LEFT JOIN COMMENTS
      ON COMMENTS.ID_NEWS = NEWS_AUTHORS.NEWS_ID
      GROUP BY NEWS_AUTHORS.AUTHOR_ID,
        NEWS_AUTHORS.NEWS_ID
      )
    GROUP BY AUTHOR_ID
    )
  JOIN
    (SELECT NAME AS AUTHOR_NAME, ID AS AU2 FROM AUTHORS
    )
  ON AU1 = AU2
  JOIN
    (SELECT TAG_NAME AS POPULAR_TAG,
      AU4
    FROM
      (SELECT NEWS_AUTHORS.AUTHOR_ID AS AU3,
        COUNT(NEWS_TAG.TAG)          AS C_N,
        NEWS_TAG.TAG                 AS TAG_NAME
      FROM NEWS_AUTHORS
      INNER JOIN NEWS_TAG
      ON NEWS_AUTHORS.NEWS_ID = NEWS_TAG.NEWS_ID
      GROUP BY NEWS_AUTHORS.AUTHOR_ID,
        NEWS_TAG.TAG
      ORDER BY NEWS_AUTHORS.AUTHOR_ID
      )
    INNER JOIN
      (SELECT AU4,
        MAX(COUNT_NEWS) AS MM
      FROM
        (SELECT NEWS_AUTHORS.AUTHOR_ID AS AU4,
          COUNT(NEWS_TAG.TAG)          AS COUNT_NEWS,
          NEWS_TAG.TAG                 AS TAG_NAME
        FROM NEWS_AUTHORS
        INNER JOIN NEWS_TAG
        ON NEWS_AUTHORS.NEWS_ID = NEWS_TAG.NEWS_ID
        GROUP BY NEWS_AUTHORS.AUTHOR_ID,
          NEWS_TAG.TAG
        ORDER BY NEWS_AUTHORS.AUTHOR_ID
        )
      GROUP BY AU4
      ORDER BY AU4
      ) ON MM = C_N
    WHERE AU3 = AU4
    ) ON AU2  = AU4;
