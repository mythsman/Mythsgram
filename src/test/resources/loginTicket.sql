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