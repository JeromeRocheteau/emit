UPDATE `environments` 
SET 
  arch = ?, 
  os = ?,
  version = ? 
WHERE `id` = ?;