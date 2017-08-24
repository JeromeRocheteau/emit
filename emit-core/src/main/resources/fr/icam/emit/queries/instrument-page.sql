SELECT `id`, `uri`, `name`
FROM `instruments`
WHERE `deleted` IS NULL
LIMIT ?,?;