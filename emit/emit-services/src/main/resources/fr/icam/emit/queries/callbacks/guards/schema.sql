CREATE TABLE `guard_callbacks` (
  `id` bigint(22) NOT NULL,
  `test` bigint(22) NOT NULL,
  `success` bigint(22) NOT NULL,
  `failure` bigint(22) NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`test`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`success`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`failure`) REFERENCES `callbacks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
