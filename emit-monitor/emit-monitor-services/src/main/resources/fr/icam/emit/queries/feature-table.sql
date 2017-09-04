CREATE TABLE `features` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` datetime DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `factor` int(11) NOT NULL,
  `measure` bigint(20) NOT NULL,
  `instrument` bigint(20) NOT NULL,
  `measurement` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`instrument`,`name`),
  FOREIGN KEY (`measure`) REFERENCES `measures` (`id`) ON UPDATE CASCADE,
  FOREIGN KEY (`instrument`) REFERENCES `instruments` (`id`) ON UPDATE CASCADE,
  FOREIGN KEY (`measurement`) REFERENCES `measurements` (`id`)
);