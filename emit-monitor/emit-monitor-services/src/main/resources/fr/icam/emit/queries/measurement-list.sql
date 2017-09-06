SELECT 
  m.`id` AS id, 
  m.`started` AS started, 
  m.`stopped` AS stopped, 
  m.`uuid` AS uuid
FROM `measurements` m
WHERE m.`feature` = ? 
AND m.`started` IS NOT NULL
AND m.`stopped` IS NOT NULL
AND m.`deleted` IS NULL;