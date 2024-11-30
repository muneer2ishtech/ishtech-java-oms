CREATE TABLE t_user_profile (
  id            BIGINT UNSIGNED  NOT NULL PRIMARY KEY,
  first_name    VARCHAR(255)     NOT NULL,
  middle_name   VARCHAR(255)         NULL,
  last_name     VARCHAR(255)     NOT NULL,
  title         VARCHAR(255)         NULL,
  prefix        VARCHAR(255)         NULL,
  suffix        VARCHAR(255)         NULL,
  address_id    BIGINT UNSIGNED      NULL,
  mobile_phone  VARCHAR(255)         NULL,
  default_lang  VARCHAR(2)       NOT NULL DEFAULT 'en',
  is_active     BIT              NOT NULL DEFAULT b'1',
  description   TEXT                 NULL
);

ALTER TABLE t_user_profile
  ADD CONSTRAINT fk_user_profile_user
  FOREIGN KEY (id)
  REFERENCES t_user(id)
;
