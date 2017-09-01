SELECT `id`, `name`, `unit` 
FROM `measures`
WHERE `deleted` IS NULL
LIMIT ?,?;