CREATE TABLE `clients` (
  `uuid` varchar(45) NOT NULL,
  `broker` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  PRIMARY KEY (`uuid`),
  FOREIGN KEY (`broker`) REFERENCES `brokers` (`uri`),
  FOREIGN KEY (`user`) REFERENCES `users` (`username`)
);