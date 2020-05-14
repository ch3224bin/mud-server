insert into account (id, username, password) values
(1, 'user', '{bcrypt}$2a$10$4xld0kieIepZeXPtfhe/dOxqAyKfl0SGGgQXbrwtXERdaRE5el6xu'),
(2, 'user1', '{bcrypt}$2a$10$4xld0kieIepZeXPtfhe/dOxqAyKfl0SGGgQXbrwtXERdaRE5el6xu');

insert into role (id, role) values
(1, 'USER'),
(2, 'ADMIN');

insert into account_role (account_id, role_id) values
(1, 1), (1, 2), (2, 1);

insert into charactor (dtype, id, name, state) values
('player', 1, '액션가면', 'normal'),
('player', 2, 'test1234', 'character_create1');

insert into player (id, account_id) values
(1, 1),
(2, 2);

insert into charactor_bag (id, charactor_id) values
(1, 1), (2, 2);

insert into room (id, summary, description) values
(1, '테스트 중앙', '흰 빛으로 가득한 공간입니다.'),
(2, '테스트 동쪽', '흰 빛으로 가득한 공간입니다.'),
(3, '테스트 서쪽', '흰 빛으로 가득한 공간입니다.'),
(4, '테스트 남쪽', '흰 빛으로 가득한 공간입니다.'),
(5, '테스트 북쪽', '흰 빛으로 가득한 공간입니다.');

insert into charactor_room (charactor_id, room_id) values
(1, 1), (2, 1);

insert into door (id, is_locked) values
(1, 0), (2, 0), (3, 0), (4, 1), (5, 1);

insert into wayout (room_id, door_id, direction, next_room_id) values
(1, 1, '동', 2),
(2, 1, '서', 1),
(1, 2, '서', 3),
(3, 2, '동', 1),
(1, 3, '남', 4),
(4, 3, '북', 1),
(1, 4, '북', 5),
(5, 4, '남', 1);

insert into item (dtype, id, name, description, is_getable) values
('key', 1, '북쪽 열쇠', '북쪽문을 열 수 있는 열쇠이다.', true),
('container', 2, '서랍', '하얀 플라스틱으로 만들어진 1단 서랍이다.', false),
('key', 3, '남쪽 열쇠', '남쪽문을 열 수 있는 열쇠이다.', true),
('container', 4, '낡은 금고', '페인트가 벗겨지고 녹이슨 철제 금고이다.', false),
('key', 5, '금고 열쇠', '낡은 금고 열쇠이다.', true);

insert into keey (id) values
(1), (3), (5);

insert into container (id, door_id) values
(2, null), (4, 5);

insert into key_door (key_id, door_id) values
(1, 4),
(3, 3),
(5, 5);

insert into item_broker (dtype, id, item_id) values
('room', 1, 1),
('container', 2, 5),
('room', 3, 2),
('room', 4, 4),
('container', 5, 3);

insert into item_broker_room(id, room_id) values
(1, 1),
(3, 1),
(4, 1);

insert into item_broker_container(id, container_id) values
(2, 2),
(5, 4);
