SELECT count(`passphrase`) as size
FROM `tokens` t
WHERE t.`account` = ?;