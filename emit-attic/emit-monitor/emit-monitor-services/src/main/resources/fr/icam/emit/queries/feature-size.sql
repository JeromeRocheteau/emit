SELECT count(`id`) AS size
FROM `features`
WHERE `instrument` = ?  
AND `deleted` IS NULL;