SELECT 
  count(c.`uuid`) AS size
FROM `clients` c
WHERE c.`user` = ?;