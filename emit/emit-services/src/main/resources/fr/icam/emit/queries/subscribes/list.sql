SELECT 
  c.`id` AS id,
  c.`started` AS started,
  c.`stopped` AS stopped,
  c.`user` AS user,
  c.`topic` AS topic,
  cc.`uuid` AS clientUuid,
  cc.`name` AS clientName,
  cc.`broker` AS clientBroker,
  cc.`user` AS clientUser,
  cc.`open` AS clientOpen,
  b.`uri` AS brokerUri,
  b.`name` AS brokerName,
  b.`user` AS brokerUser
FROM `subscribes` c
INNER JOIN `clients` cc ON cc.`uuid` = c.`client`
INNER JOIN `brokers` b ON b.`uri` = cc.`broker`
WHERE c.`stopped` IS NULL;