UPDATE `measurements`
SET `deleted` = now()
WHERE `id` = ?;