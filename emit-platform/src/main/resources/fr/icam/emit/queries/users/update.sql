UPDATE `users`
SET `password` = md5(?)
WHERE `username` = ?;