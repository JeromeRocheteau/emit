SELECT 
  c.`uuid` AS uuid,
  c.`name` AS name,
  s.`user` AS user,
  c.`open` AS open,
  s.`control` as control,
  b.`uri` AS brokerUri,
  b.`name` AS brokerName,
  b.`user` AS brokerUser
FROM `clients` c
INNER JOIN `brokers`b ON b.`uri` = c.`broker`
INNER JOIN `shares` s ON s.`client` = c.`uuid`
WHERE c.`user` = ? AND s.`user` <> c.`user` AND b.`user`= c.`user` AND c.`open` = TRUE
LIMIT ?,?;