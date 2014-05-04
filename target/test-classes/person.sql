DROP TABLE `PERSON`;

CREATE TABLE `PERSON` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `first_name` varchar(255) default NULL,
  `last_name` varchar(255) NOT NULL,
  `active_ind` varchar(1) NOT NULL,
  `activity_dt_tm` DATE NOT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;

INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (1,"Talon","Cotton","Y","2015-03-27 01:32:28");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (2,"Acton","Martin","Y","2015-02-14 05:33:27");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (3,"Adrian","Prince","Y","2015-01-30 15:14:12");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (4,"Gemma","Avery","Y","2013-07-11 15:31:53");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (5,"Cathleen","Mayo","Y","2013-07-10 11:36:04");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (6,"Bree","Crawford","Y","2013-06-03 08:27:22");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (7,"Cassidy","Ray","Y","2013-09-14 17:36:29");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (8,"Tasha","Chapman","Y","2015-03-18 09:05:05");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (9,"Ignatius","Fitzgerald","Y","2013-06-02 12:07:53");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (10,"Melinda","Nolan","Y","2014-10-29 16:26:03");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (11,"Odessa","Roberts","Y","2014-04-03 18:52:51");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (12,"Hayfa","Gilmore","Y","2014-10-18 08:13:45");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (13,"Lars","Clarke","Y","2014-07-11 17:58:09");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (14,"Leigh","Pruitt","Y","2013-11-07 12:16:11");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (15,"Honorato","Ortiz","Y","2014-08-04 15:04:25");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (16,"Selma","Austin","Y","2013-11-23 17:09:58");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (17,"Macey","Vincent","Y","2014-04-27 20:09:06");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (18,"Avram","Paul","Y","2014-02-09 03:01:37");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (19,"Hilda","Mcfadden","Y","2014-03-01 00:37:16");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (20,"Desiree","Oconnor","Y","2014-07-08 13:41:23");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (21,"Alfreda","Lara","Y","2015-04-26 09:38:28");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (22,"Lillith","Hurst","Y","2014-06-24 21:57:59");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (23,"Jakeem","Day","Y","2013-11-03 21:15:24");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (24,"Miranda","Bullock","Y","2015-02-06 18:08:28");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (25,"Judith","Ryan","Y","2014-08-05 16:12:55");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (26,"Noelani","Wilcox","Y","2015-03-27 12:16:20");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (27,"Norman","Marks","Y","2015-02-11 15:35:19");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (28,"Joelle","Chambers","Y","2014-05-27 10:23:55");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (29,"Ross","Mooney","Y","2013-06-14 20:35:14");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (30,"Kadeem","Poole","Y","2014-10-06 00:26:59");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (31,"Garrett","Maddox","Y","2014-12-01 11:50:42");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (32,"Rosalyn","Gutierrez","Y","2014-09-29 22:28:43");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (33,"Jasmine","Andrews","Y","2015-01-25 12:57:43");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (34,"Glenna","Wiley","Y","2015-03-27 07:22:17");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (35,"Hop","Herring","Y","2014-03-26 22:04:52");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (36,"Maite","Gamble","Y","2015-02-15 17:16:09");
INSERT INTO `PERSON` (`id`,`first_name`,`last_name`,`active_ind`,`activity_dt_tm`) VALUES (37,"Myles","Weeks","Y","2013-11-14 06:57:54");
