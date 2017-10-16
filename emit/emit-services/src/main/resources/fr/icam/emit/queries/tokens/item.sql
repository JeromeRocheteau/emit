SELECT 
  t.`uuid` AS uuid,
  t.`username` AS username  
FROM `tokens` t 
WHERE t.`username` = ?