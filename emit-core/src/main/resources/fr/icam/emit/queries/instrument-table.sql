CREATE TABLE `instruments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` datetime DEFAULT NULL,
  `uri` varchar(90) NOT NULL,
  `name` varchar(254) NOT NULL,
  PRIMARY KEY (`uri`,`name`),
  UNIQUE KEY (`uri`)
);