SELECT `id`, `name`
FROM `measurands`
WHERE `deleted` IS NULL
LIMIT ?,?;