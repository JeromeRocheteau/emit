CREATE TABLE `tokens` (
  `uuid` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  PRIMARY KEY (`uuid`),
  FOREIGN KEY (`user`) REFERENCES `users` (`username`)
);