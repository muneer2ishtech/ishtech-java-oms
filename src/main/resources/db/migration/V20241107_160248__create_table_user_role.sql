CREATE TABLE t_user_role (
  id           BIGINT UNSIGNED  NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_id      BIGINT UNSIGNED  NOT NULL,
  role_name    ENUM('USER', 'ADMIN') NOT NULL,
  is_active    BIT              NOT NULL DEFAULT b'1',
  description  TEXT                 NULL
);

ALTER TABLE t_user_role
  ADD CONSTRAINT uk_user_role_user_id
  UNIQUE (user_id)
;

ALTER TABLE t_user_role
  ADD CONSTRAINT fk_user_role_user
  FOREIGN KEY (user_id)
  REFERENCES t_user(id)
;
