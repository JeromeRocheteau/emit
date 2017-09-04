SELECT 
  m.`id` AS id, 
  m.`started` AS started, 
  m.`stopped` AS stopped, 
  m.`uuid` AS uuid
FROM `measurements` m
WHERE m.`feature` = ? 
AND m.`deleted` IS NULL;