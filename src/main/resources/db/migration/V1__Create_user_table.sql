CREATE TABLE users(
    id        serial4 NOT NULL,
    full_name varchar NOT NULL,
    user_name varchar NOT NULL,
    email     varchar NOT NULL,
    password  varchar NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_unique UNIQUE (email)
);