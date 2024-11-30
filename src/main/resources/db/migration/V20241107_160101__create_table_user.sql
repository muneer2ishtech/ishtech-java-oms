CREATE TABLE t_user (
  id                     BIGINT UNSIGNED  NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username               VARCHAR(255)     NOT NULL,
  email                  VARCHAR(255)     NOT NULL,
  password_hash          VARCHAR(255)     NOT NULL,
  password_reset_token   VARCHAR(255)         NULL,
  force_change_password  BIT              NOT NULL DEFAULT b'0',
  is_email_verified      BIT              NOT NULL DEFAULT b'0',
  is_active              BIT              NOT NULL DEFAULT b'1',
  description            TEXT                 NULL
);

ALTER TABLE t_user
  ADD CONSTRAINT uk_user_username
  UNIQUE (username)
;

ALTER TABLE t_user
  ADD CONSTRAINT uk_user_email
  UNIQUE (email)
;
