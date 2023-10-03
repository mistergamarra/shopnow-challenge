CREATE TABLE IF NOT EXISTS product(
                               id int not null primary key generated always as identity,
                               uuid varchar not null,
                               name varchar not null,
                               created_by varchar not null,
                               modified_by varchar not null,
                               created_at timestamp,
                               modified_at timestamp
);