SELECT `username`
FROM `accounts`
WHERE `username` = ?
AND `password` = ?
AND `deleted` IS NOT NULL;