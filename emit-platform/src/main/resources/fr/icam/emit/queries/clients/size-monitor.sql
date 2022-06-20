SELECT 
  count(c.`uuid`) AS size
FROM `clients` c
INNER JOIN `shares` s ON s.`client` = c.`uuid`
WHERE s.`user` = ? AND s.`control` = 1;