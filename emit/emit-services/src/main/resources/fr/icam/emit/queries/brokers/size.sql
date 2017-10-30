SELECT 
  count(b.`uri`) AS size
FROM `brokers` b
WHERE b.`user` = ?;