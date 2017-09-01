SELECT 
  f.`id` AS id, 
  f.`name` AS name,
  f.`factor` AS factor, 
  m.`id` AS measureId,
  m.`name` AS measureName,
  m.`unit` AS measureUnit,
  i.`id` AS instrumentId,
  i.`uri` AS instrumentUri,
  i.`name` AS instrumentName
FROM `features` f
INNER JOIN `instruments` i ON i.`id` = f.`instrument`
INNER JOIN `measures` m ON m.`id` = f.`measure`
WHERE f.`id` = ? 
AND f.`deleted` IS NULL;