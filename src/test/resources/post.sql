DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `title` varchar(40) NOT NULL ,
  `src` varchar(100) DEFAULT '/img/1.jpg',
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `likes` int(11) DEFAULT '0',
  `valid` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `uid_index` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;