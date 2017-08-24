SELECT count(`id`) AS size
FROM `environments`
WHERE `deleted` IS NULL;