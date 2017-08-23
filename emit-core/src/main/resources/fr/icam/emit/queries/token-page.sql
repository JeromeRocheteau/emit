SELECT 
  t.`passphrase` as passphrase,
  t.`issued` as issued,
  m.`name` as measurand
FROM `tokens` t
INNER JOIN `measurands` m ON m.`id` = t.`measurand`
WHERE t.`account` = ? 
AND t.`deleted` IS NULL
ORDER BY t.`issued` ASC
LIMIT ?,?;