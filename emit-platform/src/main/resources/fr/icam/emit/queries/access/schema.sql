CREATE TABLE `access` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `issued` datetime NOT NULL,
  `token` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`token`) REFERENCES `tokens` (`uuid`)
);
