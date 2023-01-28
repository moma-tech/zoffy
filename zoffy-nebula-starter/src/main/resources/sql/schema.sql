-- ----------------------------
-- Table structure for UserInfo
-- ----------------------------

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user`
(
    `user_id`   int(20) NOT NULL,
    `email`     varchar(255) DEFAULT NULL,
    `phone_no`  varchar(255) DEFAULT NULL,
    `user_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`user_id`)
);

DROP TABLE IF EXISTS `user2`;
CREATE TABLE IF NOT EXISTS `user2`
(
    `user_id`   int(20) NOT NULL,
    `email`     varchar(255) DEFAULT NULL,
    `phone_no`  varchar(255) DEFAULT NULL,
    `user_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`user_id`)
);

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role`
(
    `role_id`   int(20) NOT NULL,
    `role_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`role_id`)
);


DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role`
(
    `ur_id`   int(20) NOT NULL,
    `role_id` int(20) NOT NULL,
    `user_id` int(20) NOT NULL,
    PRIMARY KEY (`ur_id`)
);

