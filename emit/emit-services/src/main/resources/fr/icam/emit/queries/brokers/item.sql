SELECT 
  b.`uri` AS uri,
  b.`name` AS name,
  b.`user` AS user,
  b.`username` AS username,  
  b.`password` AS password 
FROM `brokers` b
INNER JOIN `clients` c ON c.`broker` = b.`uri`
WHERE b.`user` = ? AND c.`uuid` = ?
LIMIT 0,1;