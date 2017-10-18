SELECT 
  c.`id` AS id,
  c.`started` AS started,
  c.`stopped` AS stopped,
  c.`user` AS user,
  cc.`uuid` AS clientUuid,
  cc.`broker` AS clientBroker,
  cc.`user` AS clientUser
FROM `connects` c
INNER JOIN `clients` cc ON cc.`uuid` = c.`client`;