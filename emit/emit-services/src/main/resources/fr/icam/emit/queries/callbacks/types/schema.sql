CREATE TABLE `type_callbacks` (
  `id` bigint(20) NOT NULL
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `callbacks` (`id`),
  FOREIGN KEY (`type`) REFERENCES `types` (`name`)
);