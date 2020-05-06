insert into account (id, username, password) values
(1, 'user', '{bcrypt}$2a$10$4xld0kieIepZeXPtfhe/dOxqAyKfl0SGGgQXbrwtXERdaRE5el6xu');

insert into role (id, role) values
(1, 'USER'),
(2, 'ADMIN');

insert into account_role (account_id, role_id) values
(1, 1);

insert into player (name, account_id) values
('액션가면', 1);