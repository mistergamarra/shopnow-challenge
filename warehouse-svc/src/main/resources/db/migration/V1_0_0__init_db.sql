CREATE TABLE IF NOT EXISTS warehouse(
                               id int not null primary key generated always as identity,
                               product_uuid varchar not null,
                               stock decimal not null,
                               created_by varchar not null,
                               modified_by varchar not null,
                               created_at timestamp,
                               modified_at timestamp
);