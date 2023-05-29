-- milestone 테이블에 데이터 추가
INSERT INTO milestone (title, description, create_time, is_delete)
VALUES ('Milestone 1', 'First milestone description', CURRENT_TIMESTAMP, 0),
       ('Milestone 2', 'Second milestone description', CURRENT_TIMESTAMP, 0),
       ('Milestone 3', 'Third milestone description', CURRENT_TIMESTAMP, 0);

-- member 테이블에 데이터 추가
INSERT INTO member (login_id, member_name, password, email, profile_url)
VALUES ('john123', 'John Doe', 'password1', 'john@example.com', NULL),
       ('jane456', 'Jane Smith', 'password2', 'jane@example.com', NULL),
       ('mike789', 'Mike Johnson', 'password3', 'mike@example.com', NULL);

-- issue 테이블에 데이터 추가
INSERT INTO issue (title, create_time, update_time, is_open, is_delete, milestone_id, author)
VALUES ('Issue 1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 0, 1, 1),
       ('Issue 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 0, 2, 2),
       ('Issue 3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 0, 3, 3);

-- label 테이블에 데이터 추가
INSERT INTO label (title, font_color, description, is_delete, background_color)
VALUES ('Label 1', '#FFFFFF', 'First label description', 0, '#ff0000'),
       ('Label 2', '#FFFFFF', 'Second label description', 0, '#00ff00'),
       ('Label 3', '#FFFFFF', 'Third label description', 0, '#0000ff');

INSERT INTO assignee (member_id, issue_id)
VALUES (1, 1),
       (2, 1),
       (3, 2);

-- label_list 테이블에 데이터 추가
INSERT INTO label_list (label_id, issue_id)
VALUES (1, 1),
       (2, 2),
       (3, 2);

