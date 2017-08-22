UPDATE `instruments`
SET `deleted` = now()
WHERE `id` = ?;