SELECT 
  m.`id` AS id, 
  m.`deleted` AS deleted, 
  m.`started` AS started, 
  m.`stopped` AS stopped, 
  m.`uuid` AS uuid
FROM `measurements` m
INNER JOIN `features` f ON f.`id` = m.`feature`  
WHERE m.`feature` = ? 
AND m.`deleted` IS NULL;