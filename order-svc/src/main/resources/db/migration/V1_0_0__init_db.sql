create table if not exists orders
(
                               id int not null primary key generated always as identity,
                               uuid varchar not null,
                               code varchar not null,
                               user_uuid varchar not null,
                               created_by varchar,
                               modified_by varchar,
                               created_at timestamp,
                               modified_at timestamp
);

create table if not exists item(
                                      id int not null primary key generated always as identity,
                                      order_id int,
                                      product_uuid varchar not null,
                                      quantity decimal not null,
                                      created_by varchar,
                                      modified_by varchar,
                                      created_at timestamp,
                                      modified_at timestamp,
                                        CONSTRAINT fk_orders
                                          FOREIGN KEY(order_id)
                                          REFERENCES orders(id)
);

