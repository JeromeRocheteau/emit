SELECT 
  c.`uuid` AS uuid,
  c.`name` AS name,
  c.`user` AS user,
  c.`open` AS open,
  b.`uri` AS brokerUri,
  b.`name` AS brokerName,
  b.`user` AS brokerUser
FROM `clients` c
INNER JOIN `brokers`b ON b.`uri` = c.`broker`
INNER JOIN `shares` s ON s.`client` = c.`uuid`
WHERE s.`user` = ? AND b.`user`= c.`user` AND s.`control` = 1
LIMIT ?,?;