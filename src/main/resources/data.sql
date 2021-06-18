drop table if exists bank_account CASCADE;
drop table if exists bank_transaction CASCADE;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 1 increment 1;
create table bank_account
(
    id           bigint         not null,
    balance      decimal(19, 2) not null,
    created_date timestamp      not null,
    name         varchar(20)    not null,
    updated_date timestamp      not null,
    primary key (id)
);

create table account_transaction
(
    id                   bigint         not null,
    amount               decimal(19, 2) not null,
    created_date         timestamp      not null,
    updated_date         timestamp      not null,
    account_id           bigint         not null,
    type                 varchar(20)    not null,
    primary key (id)
);
alter table account_transaction
    add constraint FK10kfmxaaoqb49mgjo30arcpk8 foreign key (account_id) references bank_account;

insert into Bank_account (id, name, balance, created_date, updated_date) values (1, '12451264127841249124', '1000', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into Bank_account (id, name, balance, created_date, updated_date) values (2, '42386369479096379646', '1000', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into account_transaction (id, amount, account_id, type, created_date, updated_date) values (1001, 100, 1, 'RECEIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into account_transaction (id, amount, account_id, type, created_date, updated_date) values (1002, 100, 2, 'SEND', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

update Bank_account set balance = 1100 where id = 1;
update Bank_account set balance = 900 where id = 2;

insert into account_transaction (id, amount, account_id, type, created_date, updated_date) values (1003, 100, 1, 'RECEIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into account_transaction (id, amount, account_id, type, created_date, updated_date) values (1004, 100, 2, 'SEND', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

update Bank_account set balance = 1200 where id = 1;
update Bank_account set balance = 800 where id = 2;

insert into account_transaction (id, amount, account_id, type, created_date, updated_date) values (1005, 50, 2, 'RECEIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into account_transaction (id, amount, account_id, type, created_date, updated_date) values (1006, 50, 1, 'SEND', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

update Bank_account set balance = 1150 where id = 1;
update Bank_account set balance = 850 where id = 2;

