CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mail_address` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `authority` int NOT NULL DEFAULT '1',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail_address` (`mail_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

INSERT INTO `users` (
  `mail_address`,
  `pass`,
  `authority`,
  `created_at`,
  `updated_at`,
  `name`
) VALUES
  ('yu.okazaki@seattleconsulting.co.jp', '666', 1, current_timestamp, current_timestamp, '悠'),
  ('yurino.sakamoto@seattleconsulting.co.jp', '777', 3, current_timestamp, current_timestamp, 'ゆりの'),
  ('keita.yamaoka@seattleconsulting.co.jp', '222', 2, current_timestamp, current_timestamp, 'けいた');

  INSERT INTO `parent_graphs` (
  `user_id`,
  `created_at`,
  `updated_at`
) VALUES
  (3, current_timestamp, current_timestamp),
  (1, current_timestamp, current_timestamp),
  (2, current_timestamp, current_timestamp);

  INSERT INTO `child_graphs` (
  `parent_id`,
  `age`,
  `score`,
  `comment`
) VALUES
  (3, 10, 100, 'aaaaaaa'),
  (3, 15, 50, 'bbbbbbb'),
  (2, 22, -90, 'ccccccc');