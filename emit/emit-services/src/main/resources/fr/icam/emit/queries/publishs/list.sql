SELECT 
  p.`id` AS id,
  p.`issued` AS issued,
  p.`user` AS user,
  p.`topic` AS topic,
  c.`uuid` AS clientUuid,
  c.`name` AS clientName,
  c.`user` AS clientUser,
  c.`open` AS clientOpen,
  b.`uri` AS brokerUri,
  b.`name` AS brokerName,
  b.`user` AS brokerUser
FROM `publishs` p
INNER JOIN `clients` c ON c.`uuid` = p.`client`
INNER JOIN `brokers` b ON b.`uri` = c.`broker`;