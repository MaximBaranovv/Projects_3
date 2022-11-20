BEGIN;

DROP TABLE IF EXISTS jdbc_user;
DROP TABLE IF EXISTS jdbc_role;

CREATE TABLE IF NOT EXISTS jdbc_role
(
    id   BIGSERIAL UNIQUE PRIMARY KEY,
    name VARCHAR(128) UNIQUE NOT NULL
);
CREATE TABLE IF NOT EXISTS jdbc_user
(
    id         BIGSERIAL UNIQUE PRIMARY KEY,
    login      VARCHAR(128) UNIQUE NOT NULL,
    password   VARCHAR(128)        NOT NULL,
    email      VARCHAR(128) UNIQUE NOT NULL,
    first_name VARCHAR(128)        NOT NULL,
    last_name  VARCHAR(128)        NOT NULL,
    birthday   DATE                NOT NULL,
    role_id    BIGINT              NOT NULL,
    CONSTRAINT fk_jdbc_role FOREIGN KEY (role_id) REFERENCES jdbc_role (id) ON DELETE CASCADE
);

INSERT INTO jdbc_role (id, name)
VALUES (1, 'ADMIN');
INSERT INTO jdbc_role (id, name)
VALUES (2, 'USER');

INSERT INTO jdbc_user (id, login, password, email, first_name, last_name, birthday, role_id)
VALUES (1, 'loginMaks', 'password1', 'maks@gmail.com', 'Maks', 'Bar', '2000-05-21', 1);
INSERT INTO jdbc_user (id, login, password, email, first_name, last_name, birthday, role_id)
VALUES (2, 'loginDima', 'password2', 'dima@gmail.com', 'Dima', 'B', '1994-05-11', 2);

COMMIT;

END;
