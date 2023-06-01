INSERT INTO `milestone` (`title`, `description`) VALUES ('Milestone 1', 'Description for Milestone 1');
INSERT INTO `milestone` (`title`, `description`) VALUES ('Milestone 2', 'Description for Milestone 2');
INSERT INTO `milestone` (`title`, `description`) VALUES ('Milestone 3', 'Description for Milestone 3');

INSERT INTO `member` (`member_id`, `member_name`, `profile_url`) VALUES (1, 'John', 'https://example.com/john');
INSERT INTO `member` (`member_id`, `member_name`, `profile_url`) VALUES (2, 'Jane', 'https://example.com/jane');
INSERT INTO `member` (`member_id`, `member_name`, `profile_url`) VALUES (3, 'Alice', 'https://example.com/alice');

INSERT INTO `issue` (`title`, `milestone_id`, `author`) VALUES ('Issue 1', 1, 1);
INSERT INTO `issue` (`title`, `milestone_id`, `author`) VALUES ('Issue 2', 2, 2);
INSERT INTO `issue` (`title`, `milestone_id`, `author`) VALUES ('Issue 3', 3, 3);

INSERT INTO `label` (`title`, `font_color`, `description`, `background_color`) VALUES ('Label 1', 'black', 'Description for Label 1', 'yellow');
INSERT INTO `label` (`title`, `font_color`, `description`, `background_color`) VALUES ('Label 2', 'white', 'Description for Label 2', 'blue');
INSERT INTO `label` (`title`, `font_color`, `description`, `background_color`) VALUES ('Label 3', 'black', 'Description for Label 3', 'green');

INSERT INTO `comment` (`contents`, `issue_id`, `member_id`) VALUES ('Comment 1', 1, 1);
INSERT INTO `comment` (`contents`, `issue_id`, `member_id`) VALUES ('Comment 2', 2, 2);
INSERT INTO `comment` (`contents`, `issue_id`, `member_id`) VALUES ('Comment 3', 3, 3);

INSERT INTO `label_list` (`label_id`, `issue_id`) VALUES (1, 1);
INSERT INTO `label_list` (`label_id`, `issue_id`) VALUES (2, 1);
INSERT INTO `label_list` (`label_id`, `issue_id`) VALUES (3, 2);

INSERT INTO `assignee` (`member_id`, `issue_id`)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);