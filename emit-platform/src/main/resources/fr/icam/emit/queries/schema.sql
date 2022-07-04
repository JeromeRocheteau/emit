/* */

DROP TABLE IF EXISTS `shares`;

DROP TABLE IF EXISTS `subscribes`;

DROP TABLE IF EXISTS `publishs`;

DROP TABLE IF EXISTS `connects`;

DROP TABLE IF EXISTS `clients`;

DROP TABLE IF EXISTS `brokers`;

DROP TABLE IF EXISTS `topics`;

/* */

DROP TABLE IF EXISTS `guard_callbacks`;

DROP TABLE IF EXISTS `storage_callbacks`;

DROP TABLE IF EXISTS `feature_callbacks`;

DROP TABLE IF EXISTS `value_callbacks`;

DROP TABLE IF EXISTS `type_callbacks`;

DROP TABLE IF EXISTS `topic_callbacks`;

DROP TABLE IF EXISTS `callbacks`;

DROP TABLE IF EXISTS `symbols`;

DROP TABLE IF EXISTS `types`;

/* */

DROP TABLE IF EXISTS `access`;

DROP TABLE IF EXISTS `tokens`;

DROP TABLE IF EXISTS `prospects`;

DROP TABLE IF EXISTS `users`;

/* */

CREATE TABLE `users` (
  `username` varchar(90) NOT NULL,
  `rolename` varchar(90) NOT NULL,
  `password` varchar(90) NOT NULL,
  PRIMARY KEY (`username`)
);

CREATE TABLE `prospects` (
  `username` varchar(90) NOT NULL,
  `rolename` varchar(90) NOT NULL,
  `password` varchar(90) NOT NULL,
  PRIMARY KEY (`username`)
);

CREATE TABLE `tokens` (
  `uuid` varchar(90) NOT NULL,
  `user` varchar(90) NOT NULL,
  PRIMARY KEY (`uuid`),
  FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `access` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `issued` datetime NOT NULL,
  `token` varchar(90) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`token`) REFERENCES `tokens` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE
);

/* */

CREATE TABLE `types` (
  `name` varchar(90) NOT NULL,
  `category` varchar(90) NOT NULL,
  PRIMARY KEY (`name`)
);

CREATE TABLE `symbols` (
  `name` varchar(90) NOT NULL,
  `html` varchar(90) NOT NULL,
  PRIMARY KEY (`name`)
);

CREATE TABLE `callbacks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(90) NOT NULL,
  `atomic` tinyint(1) NOT NULL,
  `category` varchar(90) NOT NULL,
  `issued` datetime NOT NULL,
  `user` varchar(90) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `topic_callbacks` (
  `id` bigint(20) NOT NULL,
  `topic` varchar(90) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `type_callbacks` (
  `id` bigint(20) NOT NULL,
  `type` varchar(90) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`type`) REFERENCES `types` (`name`)
);

CREATE TABLE `value_callbacks` (
  `id` bigint(20) NOT NULL,
  `type` varchar(90) NOT NULL,
  `value` varchar(90) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`type`) REFERENCES `types` (`name`)
);

CREATE TABLE `feature_callbacks` (
  `id` bigint(20) NOT NULL,
  `symbol` varchar(90) NOT NULL,
  `type` varchar(90) NOT NULL,
  `value` varchar(90) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`symbol`) REFERENCES `symbols` (`name`),
  FOREIGN KEY (`type`) REFERENCES `types` (`name`)
);

CREATE TABLE `storage_callbacks` (
  `id` bigint(20) NOT NULL,
  `collection` varchar(90) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `guard_callbacks` (
  `id` bigint(20) NOT NULL,
  `test` bigint(20) NOT NULL,
  `success` bigint(20) NOT NULL,
  `failure` bigint(20) NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`test`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`success`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`failure`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `topics` (
  `name` varchar(90) NOT NULL,
  `prefix` varchar(90) DEFAULT NULL,
  `suffix` varchar(90) NOT NULL,
  `leaf` tinyint(1) NOT NULL,
  PRIMARY KEY (`name`),
  FOREIGN KEY (`prefix`) REFERENCES `topics` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
);

/* */ 

CREATE TABLE `brokers` (
  `uri` varchar(90) NOT NULL,
  `name` varchar(90) NOT NULL,
  `user` varchar(90) NOT NULL,
  `username` varchar(90) DEFAULT NULL,
  `password` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`uri`,`user`),
  FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `clients` (
  `uuid` varchar(90) NOT NULL,
  `name` varchar(90) NOT NULL,
  `broker` varchar(90) NOT NULL,
  `user` varchar(90) NOT NULL,
  `open` tinyint(1) NOT NULL,
  `callback` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  FOREIGN KEY (`broker`) REFERENCES `brokers` (`uri`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`callback`) REFERENCES `callbacks` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE `connects` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `started` datetime NOT NULL,
  `stopped` datetime DEFAULT NULL,
  `client` varchar(90) NOT NULL,
  `user` varchar(90) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`client`) REFERENCES `clients` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `publishs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `issued` datetime NOT NULL,
  `topic` varchar(90) NOT NULL,
  `client` varchar(90) NOT NULL,
  `user` varchar(90) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`client`) REFERENCES `clients` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `subscribes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `started` datetime NOT NULL,
  `stopped` datetime DEFAULT NULL,
  `client` varchar(90) NOT NULL,
  `user` varchar(90) NOT NULL,
  `topic` varchar(90) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`client`) REFERENCES `clients` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `shares` (
  `client` varchar(90) NOT NULL,
  `user` varchar(90) NOT NULL,
  `control` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`client`,`user`),
  FOREIGN KEY (`client`) REFERENCES `clients` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`user`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
);

/* */

INSERT INTO `types` (`name`,`category`) VALUES 
("string",  "datatype"),
("boolean", "datatype"),
("integer", "datatype"),
("long",    "datatype"),
("float",   "datatype"),
("double",  "datatype"),
("date",    "datatype"),
("uuid",    "datatype"),
("uri",     "datatype");

INSERT INTO `symbols` (`name`,`html`) VALUES 
("eq",  "="),
("neq", "&ne;"),
("lt",  "&lt;"),
("leq", "&le;"),
("gt",  "&gt;"),
("geq", "&ge;");

INSERT INTO `users` (`username`,`rolename`,`password`) 
VALUES ("jerome.rocheteau@icam.fr", "admin", md5("louR8j@y2011"));