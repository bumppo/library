DELETE FROM books;
DELETE FROM users;

INSERT INTO users (name, password, role) VALUES
  ('Admin', '$2a$10$ehoRLoaW7rB3H7Y0k6maN.EeQBgdQKAfOjNnUPipSJbX9vC3PQgKS', 'ROLE_ADMIN'),
  ('Jack', '$2a$10$0llVhxDfdUH1Hqp3p1.cyO/bS50vqo3oAcuQzYMyE2hHHB33e.bQi', 'ROLE_USER'),
  ('Aaron', '$2a$10$BVTyoA3vQ8LNGw2dYxmFluoJ5yy63jmBt15Jcrv4TfYH0Y7QIruHG', 'ROLE_USER'),
  ('David', '$2a$10$c.IRvdjqHT2wnHxcvMjTt.qrtYXrZBMzMyv4dbKzUT02K32gDx4T6', 'ROLE_USER');

INSERT INTO books (name, isbn, author, user_id) VALUES
  ('Milan', '1234567890', 'Esenin', 1 ),
  ('London', '2234567890', 'Pushkin', NULL ),
  ('Paris', '3234567890', 'Gogol', NULL ),
  ('Munich', '4234567890', 'Turgenev', 2 ),
  ('Liverpool', '5234567890', 'Tolstoy', 3 ),
  ('Turin', '6234567890', 'Bulgakov', 4 ),
  ('Madrid', '7234567890', 'Lermontov', 1 ),
  ('Bern', '8234567890', 'Gorky', NULL ),
  ('Zagreb', '9234567890', 'Chekhov', 4 ),
  ('Rome', '9334567890', 'Nekrasov', 3 ),
  ('Lyon', '9434567890', 'Block', NULL ),
  ('Barcelona', '9534567890', 'Fet', 2 ),
  ('Lisbon', '9634567890', 'Bunin', 1 ),
  ('Palermo', '9734567890', 'Dostoevsky', NULL ),
  ('Manchester', '9834567890', 'Kuprin',  1 ),
  ('Valencia', '9934567890', 'Mayakovsky',  2 ),
  ('Monaco', '9944567890', 'Leskov',  4 ),
  ('Bolonga', '9954567890', 'Ostrovsky',  NULL ),
  ('Marseille', '9964567890', 'Brusov',  1 ),
  ('Amsterdam', '9974567890', 'Pasternak',  NULL ),
  ('Berlin', '9984567890', 'Balmont',  1 ),
  ('Prague', '9994567890', 'Goncharov',  3 ),
  ('Brussels', '9995567890', 'Zhukovsky',  1 ),
  ('Venice', '9996567890', 'Gumilev',  1 );
