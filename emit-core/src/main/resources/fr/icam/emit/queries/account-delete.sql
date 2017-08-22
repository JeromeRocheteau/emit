UPDATE `accounts`
SET `deleted` = now()
WHERE `username` = ?;