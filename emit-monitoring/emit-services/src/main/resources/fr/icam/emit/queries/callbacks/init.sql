SELECT 
  c.`uuid` AS uuid,
  cb.`id` AS id,
  cb.`name` AS name,
  cb.`atomic` AS atomic,
  cb.`category` AS category,
  cb.`issued` AS issued,
  cb.`user` AS user
FROM `callbacks` cb
INNER JOIN `clients` c ON c.`callback` = cb.`id`
ORDER BY cb.`id` ASC;