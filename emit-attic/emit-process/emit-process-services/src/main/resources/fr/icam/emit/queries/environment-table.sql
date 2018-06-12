CREATE TABLE `environments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` datetime DEFAULT NULL,
  `uri` varchar(90) NOT NULL,
  `arch` varchar(45) DEFAULT NULL,
  `os` varchar(45) DEFAULT NULL,
  `version` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`uri`)
);
