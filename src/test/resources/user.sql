DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gender` enum('male','female','unknown') DEFAULT 'unknown',
  `name` varchar(20) NOT NULL,
  `salt` varchar(20) NOT NULL,
  `password` varchar(40) NOT NULL,
  `avatar` varchar(40) DEFAULT NULL,
  `website` varchar(40) DEFAULT NULL,
  `biography` text DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `followers` int(11) DEFAULT '0',
  `following` int(11) DEFAULT '0',
  `posts` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE INDEX `name_index` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT  INTO `user`(name, salt, password)VALUES("myths1","aaaaa","123456");
INSERT  INTO `user`(name, salt, password)VALUES("myths2","aaaaa","123456");
INSERT  INTO `user`(name, salt, password)VALUES("myths3","aaaaa","123456");