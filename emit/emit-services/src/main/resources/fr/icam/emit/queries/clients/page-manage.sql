SELECT 
  c.`uuid` AS uuid,
  c.`broker` AS broker,
  s.`user` AS user,
  s.`control` as control
FROM `clients` c
INNER JOIN `shares` s ON s.`client` = c.`uuid`
WHERE c.`user` = ? AND s.`user` <> c.`user`
LIMIT ?,?;