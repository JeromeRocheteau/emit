SELECT `username`, `rolename`
FROM `accounts`
WHERE `username` = ?
AND `password` = ?
AND `deleted` IS NULL;