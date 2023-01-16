CREATE TABLE cleaning_types
(
    id          bigserial PRIMARY KEY,
    title       varchar(255) NOT NULL UNIQUE,
    description text,
    price       real         NOT NULL
);

CREATE TABLE additional_cleaning_favours
(
    id    bigserial PRIMARY KEY,
    title varchar(255) NOT NULL UNIQUE,
    price real         NOT NULL
);

CREATE TABLE orders
(
    id                bigserial PRIMARY KEY,
    phone_number      varchar(20) NOT NULL,
    customers_name    varchar(50) NOT NULL,
    customers_surname varchar(60) NOT NULL,
    cleaning_date     timestamp   NOT NULL,
    address           varchar(255),
    final_cost        real        NOT NULL
);


CREATE TABLE cleaning_types_orders
(
    cleaning_type_id bigint REFERENCES cleaning_types (id),
    order_id         bigint REFERENCES orders (id),
    PRIMARY KEY (cleaning_type_id, order_id)
);

CREATE TABLE additional_cleaning_favours_orders
(
    additional_cleaning_favour_id bigint REFERENCES additional_cleaning_favours (id),
    order_id                      bigint REFERENCES orders (id),
    PRIMARY KEY (additional_cleaning_favour_id, order_id)
);
