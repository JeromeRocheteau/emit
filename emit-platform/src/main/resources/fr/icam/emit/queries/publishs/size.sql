SELECT 
  count(c.`id`) AS size
FROM `publishs` c
WHERE c.`client` = ?;