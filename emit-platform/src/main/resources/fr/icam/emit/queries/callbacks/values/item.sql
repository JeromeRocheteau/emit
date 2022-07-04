SELECT 
  c.`id` AS id, 
  c.`name` AS name, 
  c.`atomic` AS atomic, 
  c.`category` AS category, 
  c.`issued` AS issued, 
  c.`user` AS user, 
  t.`name` AS typeName,
  t.`category` AS typeCategory,
  vc.`value` AS value
FROM `value_callbacks` AS vc
INNER JOIN `callbacks` AS c ON c.`id` = vc.`id`
INNER JOIN `types` AS t ON t.`name` = vc.`type`
WHERE vc.`id` = ?;