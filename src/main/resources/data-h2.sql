insert into account (id, username, password) values
(1, 'user', '{bcrypt}$2a$10$4xld0kieIepZeXPtfhe/dOxqAyKfl0SGGgQXbrwtXERdaRE5el6xu');

insert into role (id, role) values
(1, 'USER'),
(2, 'ADMIN');

insert into account_role (account_id, role_id) values
(1, 1);

insert into player (name, account_id, state) values
('액션가면', 1, 'normal');

insert into room (id, summary, description) values
(1, '테스트 중앙', '흰 빛으로 가득한 공간입니다.'),
(2, '테스트 동쪽', '흰 빛으로 가득한 공간입니다.'),
(3, '테스트 서쪽', '흰 빛으로 가득한 공간입니다.'),
(4, '테스트 남쪽', '흰 빛으로 가득한 공간입니다.'),
(5, '테스트 북쪽', '흰 빛으로 가득한 공간입니다.');

insert into player_room (player_id, room_id) values
(1, 1);

insert into door (id, is_locked) values
(1, 0), (2, 0), (3, 0), (4, 1);

insert into wayout (room_id, door_id, direction, next_room_id) values
(1, 1, '동', 2),
(2, 1, '서', 1),
(1, 2, '서', 3),
(3, 2, '동', 1),
(1, 3, '남', 4),
(4, 3, '북', 1),
(1, 4, '북', 5),
(5, 4, '남', 1);