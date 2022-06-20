CREATE TABLE `callbacks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `atomic` tinyint(1) NOT NULL,
  `category` varchar(45) NOT NULL,
  `issued` datetime NOT NULL,
  `user` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user`) REFERENCES `users` (`username`)
);