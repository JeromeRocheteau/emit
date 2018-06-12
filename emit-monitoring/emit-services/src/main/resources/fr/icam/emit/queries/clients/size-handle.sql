SELECT 
  count(c.`uuid`) AS size
FROM `clients` c
INNER JOIN `shares` s ON s.`client` = c.`uuid`
WHERE s.`user` = ? AND c.`user` <> s.`user` AND c.`open` = TRUE;