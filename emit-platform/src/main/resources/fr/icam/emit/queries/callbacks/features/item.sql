SELECT 
  c.`id` AS id, 
  c.`name` AS name, 
  c.`atomic` AS atomic, 
  c.`category` AS category, 
  c.`issued` AS issued, 
  c.`user` AS user, 
  s.`name` AS symbolName,
  s.`html` AS symbolHtml,
  t.`name` AS typeName,
  t.`category` AS typeCategory,
  fc.`value` AS value
FROM `feature_callbacks` fc
INNER JOIN `callbacks` c ON c.`id` = fc.`id`
INNER JOIN `symbols` s ON s.`name` = fc.`symbol`
INNER JOIN `types` t ON t.`name` = fc.`type`
WHERE fc.`id` = ?;