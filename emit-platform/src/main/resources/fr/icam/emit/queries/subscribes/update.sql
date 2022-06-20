UPDATE `subscribes`
SET `stopped` = now()
WHERE `id` = ?;