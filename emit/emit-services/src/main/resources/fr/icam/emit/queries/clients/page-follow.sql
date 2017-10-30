SELECT 
  c.`uuid` AS uuid,
  c.`broker` AS broker,
  c.`user` AS user,
  null as control
FROM `clients` c
WHERE c.`user` <> ? 
AND c.`uuid` NOT IN 
  (SELECT s.`client` FROM `shares` s WHERE s.`user` = ?)
LIMIT ?,?;