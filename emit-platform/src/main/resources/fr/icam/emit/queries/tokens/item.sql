SELECT 
  t.`uuid` AS uuid,
  t.`user` AS user  
FROM `tokens` t 
WHERE t.`user` = ?