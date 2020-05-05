create table account (id bigint not null auto_increment, password varchar(255) not null, username varchar(255) not null, primary key (id));

create table role (id bigint not null auto_increment, role varchar(255), primary key (id));

create table account_role (account_id bigint not null, role_id bigint not null);

alter table account add constraint UK_ACCOUNT_USERNAME unique(username);

alter table account_role add constraint FK_ACCOUNT_ROLE_01 foreign key (role_id) references role(id);

alter table account_role add constraint FK_ACCOUNT_ROLE_02 foreign key (account_id) references account(id);