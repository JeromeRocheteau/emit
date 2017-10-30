SELECT 
  count(c.`uuid`) AS size  
FROM `clients` c
WHERE c.`user` <> ? 
AND c.`uuid` NOT IN 
  (SELECT s.`client` FROM `shares` s WHERE s.`user` = ?);