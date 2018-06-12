CREATE TABLE `measurements` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` datetime DEFAULT NULL,
  `started` datetime NOT NULL,
  `stopped` datetime DEFAULT NULL,
  `uuid` varchar(45) NOT NULL,
  `feature` bigint(20) NOT NULL
  PRIMARY KEY (`id`),
  UNIQUE KEY (`uuid`),
  FOREIGN KEY (`feature`) REFERENCES `features` (`id`) ON UPDATE CASCADE,
);