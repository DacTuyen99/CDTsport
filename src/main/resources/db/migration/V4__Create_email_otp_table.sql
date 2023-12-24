CREATE TABLE email_otp(
    id            serial4   NOT NULL,
    to_email      varchar   NOT NULL,
    otp           varchar   NOT NULL,
    time_send_otp timestamp NOT NULL,
    status_check  BOOLEAN   NOT NULL DEFAULT FALSE,
    count_check   INTEGER,
    CONSTRAINT email_otp_pkey PRIMARY KEY (id),
    CONSTRAINT email_otp_unique UNIQUE (to_email)
);