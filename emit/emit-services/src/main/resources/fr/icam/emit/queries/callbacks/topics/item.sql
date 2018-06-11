SELECT 
  c.`id` AS id, 
  c.`name` AS name, 
  c.`atomic` AS atomic, 
  c.`category` AS category, 
  c.`issued` AS issued, 
  c.`user` AS user, 
  tc.`topic` AS topic
FROM `topic_callbacks` tc
INNER JOIN `callbacks` c ON c.`id` = tc.`id`
WHERE tc.`id` = ?;