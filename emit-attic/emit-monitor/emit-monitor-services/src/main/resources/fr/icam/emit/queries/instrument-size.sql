SELECT count(`id`) AS size
FROM `instruments`
WHERE `deleted` IS NULL;