SELECT 
  count(c.`id`) AS size
FROM `callbacks` c
WHERE c.`user` = ?;