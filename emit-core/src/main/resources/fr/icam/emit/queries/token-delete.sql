UPDATE `tokens`
SET `deleted` = now()
WHERE `passphrase` = ?;