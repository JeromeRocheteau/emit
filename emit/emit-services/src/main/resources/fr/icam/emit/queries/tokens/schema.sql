CREATE TABLE `tokens` (
  `uuid` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`uuid`),
  FOREIGN KEY (`username`) REFERENCES `users` (`username`)
);