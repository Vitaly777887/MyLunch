DELETE FROM votes;
DELETE FROM menu_items;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (user_id, role) VALUES
  (100, 'ROLE_USER'),
  (101, 'ROLE_ADMIN');

INSERT INTO restaurants (name) VALUES
  ('2stars'),
  ('3stars'),
  ('4stars');

INSERT INTO menu_items (restaurant_id, name, price) VALUES
  (102, 'hamburger', 500),
  (102, 'salmon', 1500),
  (103, 'pelmeni', 200),
  (104, 'shaverma', 300),
  (104, 'juice', 100),
  (104, 'beer', 170);

INSERT INTO votes (restaurant_id, user_id, date_time) VALUES
  (102, 100, '2018-01-05 10:00:00'),
  (104, 100, '2018-01-04 10:00:00'),
  (102, 101, '2018-01-04 10:00:00');