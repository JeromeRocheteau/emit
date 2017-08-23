CREATE TABLE `tokens` (
  `passphrase` varchar(45) NOT NULL,
  `account` varchar(45) NOT NULL,
  `measurand` bigint(20) NOT NULL,
  `deleted` datetime DEFAULT NULL,
  `issued` datetime NOT NULL,
  PRIMARY KEY (`passphrase`),
  FOREIGN KEY (`measurand`) REFERENCES `measurands` (`id`)
);