SELECT count(`passphrase`) AS size
FROM `tokens`
WHERE `account` = ?
AND `deleted` IS NULL;