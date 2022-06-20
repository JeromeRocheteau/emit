CREATE TABLE `topics` (
  `name` varchar(45) NOT NULL,
  `prefix` varchar(45) DEFAULT NULL,
  `suffix` varchar(45) NOT NULL,
  `leaf` tinyint(1) NOT NULL,
  PRIMARY KEY (`name`),
  FOREIGN KEY (`prefix`) REFERENCES `topics` (`name`)
);