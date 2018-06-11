SELECT 
  c.`id` AS id, 
  c.`name` AS name, 
  c.`atomic` AS atomic, 
  c.`category` AS category, 
  c.`issued` AS issued, 
  c.`user` AS user, 
  t.`name` AS typeName,
  t.`category` AS typeCategory
FROM `type_callbacks` tc
INNER JOIN `callbacks` c ON c.`id` = tc.`id`
INNER JOIN `types` t ON t.`name` = tc.`type`
WHERE tc.`id` = ?;