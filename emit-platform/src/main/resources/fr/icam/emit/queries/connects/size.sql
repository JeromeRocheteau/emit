SELECT 
  count(c.`id`) AS size
FROM `connects` c
WHERE c.`client` = ?;