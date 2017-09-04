UPDATE `environments`
SET `deleted` = now()
WHERE `id` = ?;