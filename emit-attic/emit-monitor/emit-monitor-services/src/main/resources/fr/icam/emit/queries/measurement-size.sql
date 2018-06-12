SELECT count(`id`) AS size
FROM `measurements`
WHERE `feature` = ?
AND `started` IS NOT NULL
AND `stopped` IS NOT NULL
AND `deleted` IS NULL;