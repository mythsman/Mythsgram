DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  `gender` ENUM("male","female","unknown") DEFAULT "unknown",
  `name` varchar(20) DEFAULT NULL,
  `salt` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `avatar` varchar(20) DEFAULT NULL,
  `website` varchar(20) DEFAULT NULL,
  `biography` TEXT DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `date` DATETIME DEFAULT current_timestamp,
  `followers` INT DEFAULT 0 ,
  `following` INT DEFAULT 0 ,
  `posts` INT DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
