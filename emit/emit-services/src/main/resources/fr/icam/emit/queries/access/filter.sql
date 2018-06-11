SELECT 
  a.`id` as id,
  a.`issued` as issued, 
  t.`uuid` AS uuid,
  t.`user` AS user
FROM `access` a
INNER JOIN `tokens` t ON a.`token` = t.`uuid` 
WHERE t.`uuid` = ?
ORDER BY a.`issued` DESC
LIMIT 0,1;