CREATE TABLE `subscribes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `started` datetime NOT NULL,
  `stopped` datetime DEFAULT NULL,
  `client` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  `topic` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`client`) REFERENCES `clients` (`uuid`),
  FOREIGN KEY (`user`) REFERENCES `users` (`username`)
);