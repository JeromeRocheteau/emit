SELECT `id`, `uri`, `arch`, `os`, `version`
FROM `environments`
WHERE `deleted` IS NULL;