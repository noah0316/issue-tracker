DROP DATABASE issue;

CREATE DATABASE issue;

USE issue;

CREATE TABLE `milestone`
(
    `milestone_id` INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `title`        VARCHAR(45)  NOT NULL,
    `description`  VARCHAR(300) NULL,
    `create_time`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `is_delete`    BIT          NOT NULL DEFAULT 0
);



CREATE TABLE `member`
(
    `member_id`   INT          NOT NULL PRIMARY KEY,
    `member_name` VARCHAR(45)  NOT NULL,
    `profile_url` VARCHAR(300) NULL
);



CREATE TABLE `issue`
(
    `issue_id`     INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title`        VARCHAR(45) NOT NULL,
    `description`   VARCHAR(300) NULL,
    `create_time`  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_open`      BIT         NOT NULL DEFAULT 1,
    `is_delete`    BIT         NOT NULL DEFAULT 0,
    `milestone_id` INT         NULL,
    `author`       INT         NOT NULL,

    CONSTRAINT `fk_Issue_Milestone1`
        FOREIGN KEY (`milestone_id`)
            REFERENCES `milestone` (`milestone_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,

    CONSTRAINT `fk_Issue_User1`
        FOREIGN KEY (`author`)
            REFERENCES `member` (`member_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION

);



CREATE TABLE `label`
(
    `label_id`         INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `title`            VARCHAR(45)  NOT NULL,
    `font_color`       VARCHAR(45)  NOT NULL,
    `description`      VARCHAR(300) NULL,
    `is_delete`        BIT          NOT NULL DEFAULT 0,
    `background_color` VARCHAR(45)  NOT NULL
);



CREATE TABLE `comment`
(
    `comment_id`  INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `contents`    VARCHAR(45) NOT NULL,
    `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `issue_id`    INT         NOT NULL,
    `member_id`   INT         NOT NULL,

    CONSTRAINT `fk_Comment_Issue1`
        FOREIGN KEY (`issue_id`)
            REFERENCES `issue` (`issue_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_comment_user1`
        FOREIGN KEY (`member_id`)
            REFERENCES `member` (`member_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);



CREATE TABLE `label_list`
(
    `label_list_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `label_id`      INT NOT NULL,
    `issue_id`      INT NOT NULL,

    CONSTRAINT `fk_Label_has_Issue_Label1`
        FOREIGN KEY (`label_id`)
            REFERENCES `label` (`label_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_Label_has_Issue_Issue1`
        FOREIGN KEY (`issue_id`)
            REFERENCES `issue` (`issue_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);



CREATE TABLE `assignee`
(
    `assignee_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `member_id`   INT NOT NULL,
    `issue_id`    INT NOT NULL,

    CONSTRAINT `fk_User_has_Issue_User1`
        FOREIGN KEY (`member_id`)
            REFERENCES `member` (`member_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_User_has_Issue_Issue1`
        FOREIGN KEY (`issue_id`)
            REFERENCES `issue` (`issue_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);



CREATE TABLE `file`
(
    `file_id`  INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `file_url` VARCHAR(300) NULL
);



CREATE TABLE `emoji`
(
    `emoji_id`    INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `emoji_shape` VARCHAR(45) NULL,
    `emoji_count` INT         NULL
);


CREATE TABLE `issue_file_list`
(
    `issue_id` INT NOT NULL,
    `file_id`  INT NOT NULL,
    PRIMARY KEY (`issue_id`, `file_id`),

    CONSTRAINT `fk_issue_has_file_issue1`
        FOREIGN KEY (`issue_id`)
            REFERENCES `issue` (`issue_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_issue_has_file_file1`
        FOREIGN KEY (`file_id`)
            REFERENCES `file` (`file_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


CREATE TABLE `comment_file_list`
(
    `file_id`    INT NOT NULL,
    `comment_id` INT NOT NULL,
    PRIMARY KEY (`file_id`, `comment_id`),
    CONSTRAINT `fk_file_has_comment_file1`
        FOREIGN KEY (`file_id`)
            REFERENCES `file` (`file_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_file_has_comment_comment1`
        FOREIGN KEY (`comment_id`)
            REFERENCES `comment` (`comment_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


CREATE TABLE `comment_emoji_list`
(
    `emoji_id`   INT NOT NULL,
    `comment_id` INT NOT NULL,
    PRIMARY KEY (`emoji_id`, `comment_id`),

    CONSTRAINT `fk_emoji_has_comment_emoji1`
        FOREIGN KEY (`emoji_id`)
            REFERENCES `emoji` (`emoji_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_emoji_has_comment_comment1`
        FOREIGN KEY (`comment_id`)
            REFERENCES `comment` (`comment_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


CREATE TABLE `issue_emoji_list`
(
    `issue_issue_id` INT NOT NULL,
    `emoji_emoji_id` INT NOT NULL,
    PRIMARY KEY (`issue_issue_id`, `emoji_emoji_id`),

    CONSTRAINT `fk_issue_has_emoji_issue1`
        FOREIGN KEY (`issue_issue_id`)
            REFERENCES `issue` (`issue_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_issue_has_emoji_emoji1`
        FOREIGN KEY (`emoji_emoji_id`)
            REFERENCES `emoji` (`emoji_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);