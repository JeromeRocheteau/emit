UPDATE `measurementSets`
SET `deleted` = now()
WHERE `id` = ?;