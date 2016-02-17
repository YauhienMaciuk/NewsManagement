--SQL Statement to calculate each table size in your schema.

SELECT segment_name,
  SUM(BYTES) / 1024 / 1024 MB
FROM user_segments
WHERE segment_name IN
  (SELECT table_name FROM user_tables
  )
GROUP BY segment_name
ORDER BY SUM(BYTES) / 1024 / 1024 DESC;