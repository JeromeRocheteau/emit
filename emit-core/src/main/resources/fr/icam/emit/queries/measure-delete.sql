UPDATE `measures`
SET `deleted` = now()
WHERE `id` = ?;