create table if not exists account(
                               id int not null primary key generated always as identity,
                               uuid varchar not null,
                               username varchar not null,
                               password varchar not null,
                               role varchar not null,
                               account_type varchar not null,
                               created_by varchar not null,
                               modified_by varchar not null,
                               created_at timestamp,
                               modified_at timestamp
);

create table if not exists role(
                              id int not null primary key generated always as identity,
                              description varchar not null,
                              created_at timestamp,
                              modified_at timestamp
);

insert into role(description, created_at, modified_at) VALUES ('admin',now(),now());
insert into role(description, created_at, modified_at) VALUES ('customer',now(),now());



