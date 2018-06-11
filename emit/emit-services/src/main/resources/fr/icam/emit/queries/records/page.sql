(
  SELECT 
    c.`id` AS id, 
    c.`started` AS started, 
    c.`stopped` AS stopped, 
    c.`client` AS client, 
    c.`user` AS user,
    NULL AS topic,
    "connect" AS type
  FROM `connects` c
  INNER JOIN `clients` cc ON c.`client` = cc.`uuid`
  WHERE c.`client` = ?
)
UNION 
(
  SELECT 
    c.`id` AS id, 
    c.`started` AS started, 
    c.`stopped` AS stopped, 
    c.`client` AS client, 
    c.`user` AS user, 
    c.`topic` AS topic, 
    "subscribe" AS type
  FROM `subscribes` c
  INNER JOIN `clients` cc ON c.`client` = cc.`uuid`
  WHERE c.`client` = ?
)
UNION 
(
  SELECT 
    c.`id` AS id, 
    c.`issued` AS started, 
    NULL AS stopped, 
    c.`client` AS client, 
    c.`user` AS user, 
    c.`topic` AS topic,
    "publish" AS type
  FROM `publishs` c
  INNER JOIN `clients` cc ON c.`client` = cc.`uuid`
  WHERE c.`client` = ?
)
ORDER BY started DESC
LIMIT ?,?;