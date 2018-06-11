CREATE TABLE `shares` (
  `client` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  `control` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`client`,`user`),
  FOREIGN KEY (`client`) REFERENCES `clients` (`uuid`),
  FOREIGN KEY (`user`) REFERENCES `users` (`username`)
);