UPDATE `features`
SET `deleted` = now()
WHERE `id` = ?;