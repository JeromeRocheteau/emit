SELECT count(`id`) AS size 
FROM `measures`
WHERE `deleted` IS NULL;