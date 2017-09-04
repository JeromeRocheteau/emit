CREATE TABLE `measurands` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` datetime DEFAULT NULL,
  `process` varchar(90) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`process`)
);