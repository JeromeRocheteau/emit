CREATE TABLE `brokers` (
  `uri` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`uri`,`user`),
  FOREIGN KEY (`user`) REFERENCES `users` (`username`)
);