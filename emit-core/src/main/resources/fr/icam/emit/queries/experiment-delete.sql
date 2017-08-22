UPDATE `experiments`
SET `deleted` = now()
WHERE `id` = ?;