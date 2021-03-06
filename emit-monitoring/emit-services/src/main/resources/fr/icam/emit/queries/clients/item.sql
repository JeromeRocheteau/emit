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
WHERE b.`user` = c.`user`
AND c.`user` = ?
AND c.`uuid` = ?
LIMIT 0,1;