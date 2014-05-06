DROP TABLE `SHIFT`;

CREATE TABLE `SHIFT` (
  `id` int(8) unsigned NOT NULL auto_increment,
  `label` varchar(50) default NULL,
  `min_assigned` int(3) NOT NULL,
  `max_assigned` int(3) NOT NULL,
  `active_ind` varchar(1) NOT NULL,
  `activity_dt_tm` DATE NOT NULL,
  `created_dt_tm` DATE NOT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;


DROP TABLE `SHIFT_ASSIGNMENT`;

CREATE TABLE `SHIFT_ASSIGNMENT` (
  `id` int(8) unsigned NOT NULL auto_increment,
  `person_id` int(8) unsigned NOT NULL,
  `shift_id` int(8) unsigned NOT NULL
  `active_ind` varchar(1) NOT NULL,
  `activity_dt_tm` DATE NOT NULL,
  `created_dt_tm` DATE NOT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;



INSERT INTO `shift` (`id`,`label`,`min_assigned`,`max_assigned`,`active_ind`,`activity_dt_tm`,`created_dt_tm`) VALUES (1,"eu",6,3,"Y","2014-05-05 00:00:00","2013-08-07 20:03:57");
INSERT INTO `shift` (`id`,`label`,`min_assigned`,`max_assigned`,`active_ind`,`activity_dt_tm`,`created_dt_tm`) VALUES (2,"nisl.",8,8,"Y","2014-05-05 00:00:00","2014-02-22 04:55:06");
INSERT INTO `shift` (`id`,`label`,`min_assigned`,`max_assigned`,`active_ind`,`activity_dt_tm`,`created_dt_tm`) VALUES (3,"augue",1,5,"Y","2014-05-05 00:00:00","2013-08-09 05:34:46");
INSERT INTO `shift` (`id`,`label`,`min_assigned`,`max_assigned`,`active_ind`,`activity_dt_tm`,`created_dt_tm`) VALUES (4,"libero",1,5,"Y","2014-05-05 00:00:00","2014-02-18 11:20:41");
INSERT INTO `shift` (`id`,`label`,`min_assigned`,`max_assigned`,`active_ind`,`activity_dt_tm`,`created_dt_tm`) VALUES (5,"molestie",8,5,"Y","2014-05-05 00:00:00","2014-02-21 04:49:33");
INSERT INTO `shift` (`id`,`label`,`min_assigned`,`max_assigned`,`active_ind`,`activity_dt_tm`,`created_dt_tm`) VALUES (6,"magna.",4,9,"Y","2014-05-05 00:00:00","2013-12-15 16:31:15");
INSERT INTO `shift` (`id`,`label`,`min_assigned`,`max_assigned`,`active_ind`,`activity_dt_tm`,`created_dt_tm`) VALUES (7,"feugiat",10,10,"Y","2014-05-05 00:00:00","2014-02-25 07:28:51");
INSERT INTO `shift` (`id`,`label`,`min_assigned`,`max_assigned`,`active_ind`,`activity_dt_tm`,`created_dt_tm`) VALUES (8,"nec,",8,2,"Y","2014-05-05 00:00:00","2013-05-30 04:10:57");
INSERT INTO `shift` (`id`,`label`,`min_assigned`,`max_assigned`,`active_ind`,`activity_dt_tm`,`created_dt_tm`) VALUES (9,"Lorem",4,1,"Y","2014-05-05 00:00:00","2013-06-20 10:28:37");
INSERT INTO `shift` (`id`,`label`,`min_assigned`,`max_assigned`,`active_ind`,`activity_dt_tm`,`created_dt_tm`) VALUES (10,"ullamcorper.",5,8,"Y","2014-05-05 00:00:00","2013-08-13 18:41:38");