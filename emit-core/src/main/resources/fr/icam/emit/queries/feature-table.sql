CREATE TABLE `features` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` datetime DEFAULT NULL,
  `name` varchar(254) NOT NULL,
  `factor` int(11) NOT NULL,
  `measure` bigint(20) NOT NULL,
  `instrument` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`measure`) REFERENCES `measures` (`id`),
  FOREIGN KEY (`instrument`) REFERENCES `instruments` (`id`)
);
