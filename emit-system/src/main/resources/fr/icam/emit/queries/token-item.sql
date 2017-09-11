SELECT 
  t.`id` AS id, 
  t.`uuid` AS uuid,
  t.`username` AS username  
FROM `tokens` t 
WHERE t.`username` = ?
AND t.`feature` = ?
AND t.`deleted` IS NULL;