SELECT `id`, `deleted`, `started`, `stopped`, `uuid`
FROM `measurements`
WHERE `feature` = ? AND `deleted` IS NULL;