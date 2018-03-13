SELECT 
  c.`uuid` AS uuid,
  c.`name` AS name,
  c.`broker` AS broker,
  c.`user` AS user,
  c.`open` AS open
FROM `clients` c
WHERE c.`user` = ?
LIMIT ?,?;