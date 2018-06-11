SELECT 
  count(c.`id`) AS size
FROM `subscribes` c
WHERE c.`client` = ?;