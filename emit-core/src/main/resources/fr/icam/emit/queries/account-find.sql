SELECT `username`
FROM `accounts`
WHERE `username` = ? 
AND `deleted` IS NULL;