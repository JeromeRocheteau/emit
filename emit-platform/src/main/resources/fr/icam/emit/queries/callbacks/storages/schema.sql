CREATE TABLE `storage_callbacks` (
  `id` bigint(22) NOT NULL,
  `collection` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
