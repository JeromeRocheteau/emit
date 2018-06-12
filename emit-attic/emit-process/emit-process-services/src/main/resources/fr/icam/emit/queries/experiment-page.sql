SELECT 
  e.`id` AS id,
  e.`started` AS started,
  e.`stopped` AS stopped,
  m.`id` AS measurandId,
  m.`process` AS measurandProcess,
  s.`id` AS environementId,
  s.`uri` AS environementUri,
  s.`arch` AS environementArch,
  s.`os` AS environementOs,
  s.`version` AS environementVersion
FROM `experiments` e
INNER JOIN `measurands` m ON m.`id` = e.`measurand` 
INNER JOIN `environements` s ON s.`id` = e.`environement` 
WHERE e.`measurand` = ? 
AND e.`deleted` IS NULL 
AND e.`experiment` IS NULL
LIMIT ?,?;