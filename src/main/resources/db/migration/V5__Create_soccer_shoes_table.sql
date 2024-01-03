CREATE TABLE soccer_shoes(
    id                    serial4   NOT NULL,
    brand                 varchar   NOT NULL,
    description           varchar   NOT NULL,
    time_post             timestamp NOT NULL,
    price                 INTEGER   NOT NULL,
    sale                  INTEGER   NOT NULL,
    price_sale            INTEGER   NOT NULL,
    image_id              INTEGER[] NULL,
    CONSTRAINT soccer_shoes_pkey PRIMARY KEY (id)
);