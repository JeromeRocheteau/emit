SELECT 
  cb.`id` AS id,
  cb.`name` AS name,
  cb.`atomic` AS atomic,
  cb.`category` AS category,
  cb.`issued` AS issued,
  cb.`user` AS user
FROM `shares` s
INNER JOIN `clients` c ON s.`client` = c.`uuid`
INNER JOIN `callbacks` cb ON cb.`user` = c.`user` 
WHERE s.`user` = ? AND s.`control` = 1
GROUP BY cb.`id`
ORDER BY cb.`id` ASC;