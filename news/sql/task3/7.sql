--SQL statement to output total/available space within 
--each tablespace. Calculate â€œUsed %â€? column value.

SELECT df.tablespace_name "Tablespace",
  ROUND(100      * tu.totalusedspace / df.totalspace) "Used %",
  (df.totalspace - tu.totalusedspace) "Free MB",
  df.totalspace "Total MB"
FROM
  (SELECT tablespace_name,
    ROUND(SUM(bytes) / 1048576) TotalSpace
  FROM dba_data_files
  GROUP BY tablespace_name
  ) df,
  (SELECT ROUND(SUM(bytes)/(1024*1024)) totalusedspace,
    tablespace_name
  FROM dba_segments
  GROUP BY tablespace_name
  ) tu
WHERE df.tablespace_name = tu.tablespace_name;
