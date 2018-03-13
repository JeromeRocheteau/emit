SELECT 
  p.`id` AS id,
  p.`issued` AS issued,
  p.`user` AS user,
  p.`topic` AS topic,
  c.`uuid` AS clientUuid,
  c.`name` AS clientName,
  c.`broker` AS clientBroker,
  c.`user` AS clientUser,
  c.`open` AS clientOpen
FROM `publishs` p
INNER JOIN `clients` c ON c.`uuid` = p.`client`;