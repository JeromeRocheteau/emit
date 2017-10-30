SELECT 
  c.`uuid` AS uuid,
  c.`broker` AS broker,
  c.`user` AS user  
FROM `clients` c
INNER JOIN `shares` s ON s.`client` = c.`uuid`
WHERE s.`user` = ? AND s.`control` IS NOT NULL
LIMIT ?,?;