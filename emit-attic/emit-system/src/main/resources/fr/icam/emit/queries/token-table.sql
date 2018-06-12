CREATE TABLE `tokens` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` datetime DEFAULT NULL,
  `feature` bigint(20) NOT NULL,
  `uuid` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`feature`) REFERENCES `features` (`id`),
  FOREIGN KEY (`username`) REFERENCES `accounts` (`username`)
);