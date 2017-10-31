SELECT 
  c.`id` AS id,
  c.`started` AS started,
  c.`stopped` AS stopped,
  c.`user` AS user,
  c.`topic` AS topic,
  cc.`uuid` AS clientUuid,
  cc.`broker` AS clientBroker,
  cc.`user` AS clientUser,
  cc.`open` AS clientOpen
FROM `subscribes` c
INNER JOIN `clients` cc ON cc.`uuid` = c.`client`
INNER JOIN `shares` s ON s.`client` = cc.`uuid`
WHERE cc.`uuid` = ? AND s.`control` = 1
AND c.`stopped` IS NULL;