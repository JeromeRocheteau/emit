UPDATE `experiments` 
SET `stopped` = now()
WHERE `id` = ?;