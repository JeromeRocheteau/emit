SELECT 
  c.`id` AS id,
  c.`started` AS started,
  c.`stopped` AS stopped,
  c.`user` AS user,
  b.`username` AS username,
  b.`password` AS password,
  cc.`uuid` AS clientUuid,
  cc.`name` AS clientName,
  cc.`broker` AS clientBroker,
  cc.`user` AS clientUser,
  cc.`open` AS clientOpen
FROM `connects` c
INNER JOIN `clients` cc ON cc.`uuid` = c.`client`
LEFT JOIN `brokers` b ON b.`uri` = cc.`broker`
WHERE b.`user` = c.`user` AND c.`stopped` IS NULL;