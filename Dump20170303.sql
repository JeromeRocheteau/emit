
CREATE TABLE `measures` (
  `name` varchar(45) NOT NULL,
  `unit` varchar(45) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `measurands` (
  `process` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`process`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `environments` (
  `uri` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`uri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `instruments` (
  `uri` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`uri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `experiments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `started` datetime NOT NULL,
  `stopped` datetime NOT NULL,
  `measurand` varchar(255) NOT NULL,
  `environment` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `experiment_measurand_fk` FOREIGN KEY (`measurand`) REFERENCES `measurands` (`process`),
  CONSTRAINT `experiment_environment_fk` FOREIGN KEY (`environment`) REFERENCES `environments` (`uri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `measurementsets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` varchar(255) NOT NULL,
  `achieved` datetime NOT NULL,
  `experiment` bigint(20) NOT NULL,
  `instrument` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `measurementset_experiment_fk` FOREIGN KEY (`experiment`) REFERENCES `experiments` (`id`),
  CONSTRAINT `measurementset_instrument_fk` FOREIGN KEY (`instrument`) REFERENCES `instruments` (`uri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `measurements` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` varchar(255) DEFAULT NULL,
  `measurementset` bigint(20) NOT NULL,
  `measure` varchar(45) NOT NULL,
  `feature` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `measurement_measurementset_fk` FOREIGN KEY (`measurementset`) REFERENCES `measurementsets` (`id`),
  CONSTRAINT `measurement_measure_fk` FOREIGN KEY (`measure`) REFERENCES `measures` (`name`),
  CONSTRAINT `measurement_feature_fk` FOREIGN KEY (`feature`) REFERENCES `feature` (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `features` (
  `id`bigint(20) NOT NULL AUTO_INCREMENT,
  `measure` varchar(45) NOT NULL,
  `instruments` varchar(45) NOT NULL,
  `unit` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `feature_instrument_fk` FOREIGN KEY (`instruments`) REFERENCES `instruments` (`uri`),  
  CONSTRAINT `feature_measure_fk` FOREIGN KEY (`measure`) REFERENCES `measure` (`name`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `analysis` (
  `url` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `results` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `analysis` varchar(255) NOT NULL,
  `measure` varchar(45) DEFAULT NULL,
  `context` varchar(45) NOT NULL,
  `value` double DEFAULT NULL,
  `conditions` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_results_2_idx` (`measure`),
  KEY `fk_results_1_idx` (`analysis`),
  CONSTRAINT `fk_results_1` FOREIGN KEY (`analysis`) REFERENCES `analysis` (`url`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_results_2` FOREIGN KEY (`measure`) REFERENCES `measures` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `username` varchar(255) CHARACTER SET latin1 NOT NULL,
  `firstname` varchar(255) CHARACTER SET latin1 NOT NULL,
  `lastname` varchar(255) CHARACTER SET latin1 NOT NULL,
  `type` varchar(255) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `account` (
  `username` varchar(255) CHARACTER SET latin1 NOT NULL,
  `password` varchar(255) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `FK_de34gsw4qt8auhffbna969ahp` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cookie` (
  `passphrase` varchar(45) CHARACTER SET latin1 NOT NULL,
  `username` varchar(45) CHARACTER SET latin1 NOT NULL,
  `issued` datetime NOT NULL,
  PRIMARY KEY (`passphrase`),
  KEY `fk_Cookie_1_idx` (`username`),
  CONSTRAINT `fk_Cookie_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





