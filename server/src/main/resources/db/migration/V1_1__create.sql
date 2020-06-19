DROP TABLE IF EXISTS `user`;

CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime NOT NULL,
  `date_deleted` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `date` varchar(255) NOT NULL,
  `alarm` varchar(255) NOT NULL,
  `agent` varchar(255) DEFAULT NULL,
  `deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `certificate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_deleted` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `issuer_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `serial_number` varchar(255) NOT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `end_date` varchar(255) NOT NULL,
  `cert` varchar(3000) NOT NULL,
  `date_created` datetime NOT NULL,
  `deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `date_updated` datetime DEFAULT NULL,
    `email` varchar(255) NOT NULL,
    `first_name` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `role` varchar(255) DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `date_deleted` datetime DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `user` (`id`, `email`, `password`, `first_name`, `last_name`,
 `role`, `deleted`, `date_created`, `date_deleted`, `date_updated`) VALUES

('1', 'ivan.sredojevic@gmail.com', '$2a$10$gDqfRHdeFeiopzJ3MSNiLOdYsTDU/RJWhTr5OJvGHE3355C5OLDDm', 'Ivan',
 'Sredojevic', 'ADMIN', 0, '2020-01-01 00:00:00','2020-01-01 00:00:00', '2020-01-01 00:00:00');