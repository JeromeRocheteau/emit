CREATE TABLE `measurementSets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` datetime DEFAULT NULL,
  `achieved` datetime DEFAULT NULL,
  `experiment` bigint(20) NOT NULL,
  `content` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`experiment`) REFERENCES `experiments` (`id`)
);