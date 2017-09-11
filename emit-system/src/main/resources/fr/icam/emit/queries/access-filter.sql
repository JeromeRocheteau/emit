SELECT 
  a.`id` as id,
  a.`issued` as issued,
  t.`id` AS tokenId, 
  t.`uuid` AS tokenUuid,
  t.`username` AS tokenUsername,
  f.`id` AS featureID,
  f.`name` AS featureName,
  f.`factor` AS featureFactor,
  m.`id` AS measureId,
  m.`name` AS measureName,
  m.`unit` AS measureUnit,
  i.`id` AS instrumentId,
  i.`name` AS instrumentName,
  i.`uri` AS instrumentUri
FROM `access` a
INNER JOIN `tokens` t ON a.`token` = t.`id` 
INNER JOIN `features` f ON f.`id` = t.`feature` 
INNER JOIN `instruments` i ON i.`id` = f.`instrument`
INNER JOIN `measures` m ON m.`id` = f.`measure`
WHERE t.`uuid` = ?
AND t.`deleted` IS NULL
ORDER BY a.`issued` DESC
LIMIT 0,1;