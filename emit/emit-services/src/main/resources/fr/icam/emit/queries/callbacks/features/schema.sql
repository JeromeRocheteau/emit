CREATE TABLE `feature_callbacks` (
  `id` bigint(20) NOT NULL,
  `symbol` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`symbol`) REFERENCES `symbols` (`name`),
  FOREIGN KEY (`type`) REFERENCES `types` (`name`)
);