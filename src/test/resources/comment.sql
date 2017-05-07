DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `content` text NOT NULL,
  `post_id` int(11) NOT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `valid` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `post_id_index` (`post_id`),
  KEY `uid_index`(`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `comment` (uid,post_id,content) VALUES ('1','1',"hahahaha");
INSERT INTO `comment` (uid,post_id,content) VALUES ('1','1',"<div>233</div>");
