SELECT 
  p.`id` AS id,
  p.`issued` AS issued,
  p.`user` AS user,
  p.`topic` AS topic,
  c.`uuid` AS clientUuid,
  c.`broker` AS clientBroker,
  c.`user` AS clientUser
FROM `publishs` p
INNER JOIN `clients` c ON c.`uuid` = p.`client`;