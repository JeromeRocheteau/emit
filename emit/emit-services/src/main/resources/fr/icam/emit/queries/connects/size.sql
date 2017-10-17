SELECT 
  count(c.`id`) AS size
FROM `connects` c
INNER JOIN `shares` s ON s.`client` = c.`uuid`
WHERE s.`user` = ? AND s.`control` = 1;