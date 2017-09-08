SELECT 
  mm.`id` AS id,
  mm.`started` AS started,
  mm.`stopped` AS stopped,
  mm.`uuid` AS uuid,
  m.`id` AS measureId,
  m.`name` AS measureName,
  m.`unit` AS measureUnit,
  i.`id` AS instrumentId,
  i.`name` AS instrumentName,
  i.`uri` AS instrumentUri,
  f.`id` AS featureId,
  f.`name` AS featureName,
  f.`factor` AS featureFactor
FROM `measurements` mm
INNER JOIN `features` f ON f.`id` = mm.`feature`
INNER JOIN `instruments` i ON i.`id` = f.`instrument`
INNER JOIN `measures` m ON m.`id` = f.`measure`
WHERE m.`name` = ?
AND mm.`started` >= ?
AND mm.`stopped` <= ?
AND mm.`deleted` IS NULL;