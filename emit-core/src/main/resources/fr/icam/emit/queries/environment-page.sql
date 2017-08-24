SELECT `id`, `uri`, `name`, `arch`, `os`, `version`
FROM `environments`
WHERE `deleted` IS NULL
LIMIT ?,?;