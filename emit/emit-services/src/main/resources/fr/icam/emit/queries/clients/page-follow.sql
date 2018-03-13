SELECT 
  c.`uuid` AS uuid,
  c.`name` AS name,
  c.`broker` AS broker,
  c.`user` AS user,
  c.`open` AS open,
  null as control
FROM `clients` c
WHERE c.`user` <> ? AND c.`open` = TRUE
AND c.`uuid` NOT IN 
  (SELECT s.`client` FROM `shares` s WHERE s.`user` = ?)
LIMIT ?,?;