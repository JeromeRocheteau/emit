UPDATE `tokens`
SET `expired` = now()
WHERE `passphrase` = ?;