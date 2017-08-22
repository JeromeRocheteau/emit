SELECT count(`id`) AS size 
FROM `measurands`
WHERE `deleted` IS NOT NULL;