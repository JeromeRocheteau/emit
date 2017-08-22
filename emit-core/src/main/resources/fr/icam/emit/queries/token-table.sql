CREATE TABLE `tokens` (
  `passphrase` varchar(45) NOT NULL,
  `account` varchar(45) NOT NULL,
  `measurand` bigint(20) NOT NULL,
  `expired` datetime DEFAULT NULL,
  PRIMARY KEY (`passphrase`),
  FOREIGN KEY (`measurand`) REFERENCES `measurands` (`id`)
);
