CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  -- `authority` int NOT NULL DEFAULT '1',
  -- `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  -- `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `user_roles` (
  `user_id` bigint not null,
  `role_id` int not null,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`,`role_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `parent_graphs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `parent_graphs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `child_graphs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint NOT NULL,
  `age` int NOT NULL,
  `score` int NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `parent_id` (`parent_id`,`age`),
  CONSTRAINT `child_graphs_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `parent_graphs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  INSERT INTO `roles` (
  `name`
)VALUES
('ROLE_USER'),
('ROLE_ADMIN'),
('ROLE_OWNER');
