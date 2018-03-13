SELECT 
  c.`uuid` AS uuid,
  c.`name` AS name,
  c.`broker` AS broker,
  c.`user` AS user,
  c.`open` AS open,
  s.`control` as control
FROM `clients` c
INNER JOIN `shares` s ON s.`client` = c.`uuid`
WHERE s.`user` = ? AND s.`user` <> c.`user` AND c.`open` = TRUE
LIMIT ?,?;