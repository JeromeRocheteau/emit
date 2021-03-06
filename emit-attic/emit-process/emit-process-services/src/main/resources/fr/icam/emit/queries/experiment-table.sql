CREATE TABLE `experiments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` datetime DEFAULT NULL,
  `measurand` bigint(20) NOT NULL,
  `environment` bigint(20) NOT NULL,
  `experiment` bigint(20) DEFAULT NULL,
  `started` datetime DEFAULT NULL,
  `stopped` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`environment`) REFERENCES `environments` (`id`),
  FOREIGN KEY (`measurand`) REFERENCES `measurands` (`id`),
  FOREIGN KEY (`experiment`) REFERENCES `experiments` (`id`)
);
