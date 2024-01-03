CREATE TABLE product_img(
    id         serial4   NOT NULL,
    "name"     VARCHAR   NOT NULL,
    "type"     VARCHAR   NOT NULL,
    url_img    VARCHAR   NOT NULL,
    CONSTRAINT product_img_pkey PRIMARY KEY (id)
);