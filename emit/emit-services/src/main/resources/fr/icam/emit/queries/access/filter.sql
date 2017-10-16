SELECT 
  a.`id` as id,
  a.`issued` as issued, 
  t.`uuid` AS uuid,
  t.`username` AS username
FROM `access` a
INNER JOIN `tokens` t ON a.`token` = t.`id` 
WHERE t.`uuid` = ?
ORDER BY a.`issued` DESC
LIMIT 0,1;