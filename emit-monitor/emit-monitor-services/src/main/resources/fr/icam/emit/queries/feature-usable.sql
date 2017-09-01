SELECT 
  `measurmentId` AS measurement
FROM `features`
WHERE `id` = ? 
AND `deleted` IS NULL;