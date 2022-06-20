SELECT 
  c.`uuid` AS uuid,
  c.`name` AS name,
  c.`user` AS user,
  c.`open` AS open,
  null as control,
  b.`uri` AS brokerUri,
  b.`name` AS brokerName,
  b.`user` AS brokerUser
FROM `clients` c
INNER JOIN `brokers`b ON b.`uri` = c.`broker`
WHERE c.`user` <> ? AND b.`user`= c.`user` AND c.`open` = TRUE
AND c.`uuid` NOT IN 
  (SELECT s.`client` FROM `shares` s WHERE s.`user` = ?)
LIMIT ?,?;