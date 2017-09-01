UPDATE `measurements`
SET `stopped` = now()
WHERE `id` = ?;