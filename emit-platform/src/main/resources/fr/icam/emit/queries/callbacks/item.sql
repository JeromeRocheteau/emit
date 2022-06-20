SELECT 
  c.`id` AS id,
  c.`name` AS name,
  c.`atomic` AS atomic,
  c.`category` AS category,
  c.`issued` AS issued,
  c.`user` AS user
FROM `callbacks` c
WHERE c.`id` = ?;