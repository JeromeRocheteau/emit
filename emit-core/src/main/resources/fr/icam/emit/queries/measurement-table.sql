CREATE TABLE `measurements` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` datetime DEFAULT NULL,
  `achieved` datetime DEFAULT NULL,
  `measurementSet` bigint(20) NOT NULL,
  `feature` bigint(20) NOT NULL,
  `content` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`feature`) REFERENCES `features` (`id`),
  FOREIGN KEY (`measurementSet`) REFERENCES `measurementSets` (`id`)
);
