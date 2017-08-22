SELECT 
  t.`passphrase` as passphrase, 
  t.`account` as account,
  m.`name` as measurand,
  t.`expired`as expired
FROM `tokens` t
INNER JOIN `measurands` m ON m.`id` = t.`measurand`
WHERE t.`account` = ? 
AND `expired` IS NOT NULL
LIMIT ?,?;