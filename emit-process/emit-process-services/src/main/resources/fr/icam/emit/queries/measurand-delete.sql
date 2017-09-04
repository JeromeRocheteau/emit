UPDATE `measurands`
SET `deleted` = now()
WHERE `id` = ?;