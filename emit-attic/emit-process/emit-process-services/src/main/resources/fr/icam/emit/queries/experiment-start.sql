UPDATE `experiments` 
SET `started` = now()
WHERE `id` = ?;