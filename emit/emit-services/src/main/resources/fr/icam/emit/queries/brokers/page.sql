SELECT 
  b.`uri` AS uri,
  b.`name` AS name,
  b.`user` AS user,
  b.`username` AS username,  
  b.`password` AS password 
FROM `brokers` b
WHERE b.`user` = ?
LIMIT ?,?;