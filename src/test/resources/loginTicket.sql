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

insert into `login_ticket` (uid,ticket,expire,valid) values('1','aaa','2017-05-04 21:56:12',1);
insert into `login_ticket` (uid,ticket,expire,valid) values('2','bbb','2017-05-04 21:56:22',0);
insert into `login_ticket` (uid,ticket,expire,valid) values('3','ccc','2017-05-04 21:56:53',1);

