SELECT `id`, `name`
FROM `measurands`
WHERE `deleted` IS NOT NULL
LIMIT ?,?;