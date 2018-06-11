SELECT 
  c.`id` AS id, 
  c.`name` AS name, 
  c.`atomic` AS atomic, 
  c.`category` AS category, 
  c.`issued` AS issued, 
  c.`user` AS user, 
  sc.`collection` AS collection
FROM `storage_callbacks` sc
INNER JOIN `callbacks` c ON c.`id` = sc.`id`
WHERE sc.`id` = ?;