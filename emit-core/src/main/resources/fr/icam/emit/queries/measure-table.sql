CREATE TABLE `measures` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` datetime DEFAULT NULL,
  `name` varchar(254) NOT NULL,
  `unit` varchar(254) NOT NULL,
  PRIMARY KEY (`id`)
);
