create table account (id bigint not null auto_increment, password varchar(255) not null, username varchar(255) not null, primary key (id));

create table role (id bigint not null auto_increment, role varchar(255), primary key (id));

create table account_role (account_id bigint not null, role_id bigint not null);

create table player (id bigint not null auto_increment,
	name varchar(255) not null, state varchar(255) not null, account_id bigint, primary key (id));

create table room (id bigint not null auto_increment, description varchar(255) not null, summary varchar(255) not null, primary key (id));

create table player_room (room_id bigint, player_id bigint not null, primary key (player_id));

create table wayout (id bigint not null auto_increment,
	direction varchar(255) not null, room_id bigint not null,
	next_room_id bigint not null, door_id bigint not null, is_show boolean not null, primary key (id));

create table door (id bigint not null auto_increment, is_locked boolean not null, primary key (id));

alter table account add constraint UK_ACCOUNT_USERNAME unique(username);

alter table account_role add constraint FK_ACCOUNT_ROLE_01 foreign key (role_id) references role(id);

alter table account_role add constraint FK_ACCOUNT_ROLE_02 foreign key (account_id) references account(id);

alter table player add constraint UK_PLAYER_NAME unique (name);

alter table player add constraint FK_PLAYER_01 foreign key (account_id) references account(id);

alter table player_room add constraint FK_PLAYER_ROOM_01 foreign key (room_id) references room(id);

alter table player_room add constraint FK_PLAYER_ROOM_02 foreign key (player_id) references player(id);

alter table wayout add constraint FK_WAYOUT_01 foreign key (room_id) references room(id);

alter table wayout add constraint FK_WAYOUT_02 foreign key (next_room_id) references room(id);

alter table wayout add constraint FK_WAYOUT_03 foreign key (door_id) references door(id);
