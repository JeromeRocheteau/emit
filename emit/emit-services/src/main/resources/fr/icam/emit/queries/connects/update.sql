UPDATE `connects`
SET `stopped` = now()
WHERE `id` = ?;