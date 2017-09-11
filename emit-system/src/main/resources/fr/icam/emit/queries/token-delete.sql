UPDATE `tokens`
SET `deleted` = now()
WHERE `id` = ?;