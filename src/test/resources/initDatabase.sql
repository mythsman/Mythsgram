DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gender` enum('male','female','unknown') DEFAULT 'unknown',
  `name` varchar(20) NOT NULL,
  `salt` varchar(20) NOT NULL,
  `password` varchar(40) NOT NULL,
  `avatar` varchar(20) DEFAULT NULL,
  `website` varchar(20) DEFAULT NULL,
  `biography` text DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `followers` int(11) DEFAULT '0',
  `following` int(11) DEFAULT '0',
  `posts` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE INDEX `name_index` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `login_ticket`;
CREATE TABLE `login_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `ticket` varchar(40) NOT NULL,
  `expire` datetime DEFAULT CURRENT_TIMESTAMP,
  `valid` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `ticket_index` (`ticket`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;