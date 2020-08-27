INSERT INTO users (username, password, enabled, name, surname, email) VALUES('juan', '$2a$10$RDzIvlcKvbd33kdjWrvBu.qquMshz8XbKhVbKTe24z19ucihDyzj2', true, 'Juan', 'Gomez', 'juan.gomez@gmail.com');
INSERT INTO users (username, password, enabled, name, surname, email) VALUES('andres', '$2a$10$xGdNDZsElXxiGBDIIaY/J.5kcR/kcOQl0fzoWnJI6JL4cpp6HdytG', true, 'Andres', 'Perez', 'andres.perez@gmail.com');
INSERT INTO users (username, password, enabled, name, surname, email) VALUES('luis', '$2a$10$aJg8jpGdqg5WZFwqyVssQuavGdWdalyZhCLQABkUEhfEnse80/VXW', true, 'Luis', 'Lopez', 'luis.lopez@gmail.com');
INSERT INTO users (username, password, enabled, name, surname, email) VALUES('angel', '$2a$10$PV0XPyC6VxHXkHVFPYM5Y.EcPpeDk7taaKt38sWWpAl4vgkrijCq6', true, 'Angel', 'Dominguez', 'angel.dominguez@gmail.com');

INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_USER');

INSERT INTO user_roles(user_id, role_id) VALUES (1, 2);
INSERT INTO user_roles(user_id, role_id) VALUES (2, 2);
INSERT INTO user_roles(user_id, role_id) VALUES (2, 1);