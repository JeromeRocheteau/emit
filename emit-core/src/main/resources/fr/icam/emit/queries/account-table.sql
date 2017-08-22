CREATE TABLE `accounts` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `deleted` datetime DEFAULT NULL,
  PRIMARY KEY (`username`)
);
