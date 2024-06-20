INSERT INTO role (role_name) VALUES ('USER');
INSERT INTO role (role_name) VALUES ('ADMIN');
INSERT INTO role (role_name) VALUES ('MANAGER');

INSERT INTO discipline (name, description) VALUES ('100m Sprint', 'A short-distance sprint race in track and field.');
INSERT INTO discipline (name, description) VALUES ('Marathon', 'A long-distance running race of 42.195 kilometers.');
INSERT INTO discipline (name, description) VALUES ('High Jump', 'An event where athletes jump over a horizontal bar at measured heights.');
INSERT INTO discipline (name, description) VALUES ('Pole Vault', 'An event where athletes use a pole to jump over a high bar.');
INSERT INTO discipline (name, description) VALUES ('Long Jump', 'An event where athletes sprint down a runway and jump as far as possible into a sandpit.');
INSERT INTO discipline (name, description) VALUES ('Discus Throw', 'An event where athletes throw a heavy disc as far as possible.');
INSERT INTO discipline (name, description) VALUES ('Shot Put', 'An event where athletes throw a heavy spherical object called a shot as far as possible.');
INSERT INTO discipline (name, description) VALUES ('Javelin Throw', 'An event where athletes throw a spear-like object called a javelin as far as possible.');
INSERT INTO discipline (name, description) VALUES ('400m Hurdles', 'A track race where athletes must jump over hurdles placed at intervals.');
INSERT INTO discipline (name, description) VALUES ('Decathlon', 'A combined event in track and field consisting of ten events: 100m, long jump, shot put, high jump, 400m, 110m hurdles, discus, pole vault, javelin, and 1500m.');

INSERT INTO address (street, street_number, city, zip_code, country) VALUES ('Athletics Ave', 1, 'New York', '10001', 'USA');
INSERT INTO address (street, street_number, city, zip_code, country) VALUES ('Runner Rd', 123, 'Los Angeles', '90001', 'USA');
INSERT INTO address (street, street_number, city, zip_code, country) VALUES ('Sprint St', 456, 'Chicago', '60601', 'USA');
INSERT INTO address (street, street_number, city, zip_code, country) VALUES ('Marathon Blvd', 789, 'Houston', '77001', 'USA');
INSERT INTO address (street, street_number, city, zip_code, country) VALUES ('Jumper Ln', 101, 'Phoenix', '85001', 'USA');
INSERT INTO address (street, street_number, city, zip_code, country) VALUES ('Thrower St', 202, 'Philadelphia', '19101', 'USA');
INSERT INTO address (street, street_number, city, zip_code, country) VALUES ('Vaulter Ave', 303, 'San Antonio', '78201', 'USA');
INSERT INTO address (street, street_number, city, zip_code, country) VALUES ('Decathlon Rd', 404, 'San Diego', '92101', 'USA');
INSERT INTO address (street, street_number, city, zip_code, country) VALUES ('Hurdle St', 505, 'Dallas', '75201', 'USA');
INSERT INTO address (street, street_number, city, zip_code, country) VALUES ('Relay Rd', 606, 'San Jose', '95101', 'USA');

INSERT INTO club (name, address_id) VALUES ('New York Athletics Club', 1);
INSERT INTO club (name, address_id) VALUES ('Los Angeles Runners Club', 2);
INSERT INTO club (name, address_id) VALUES ('Chicago Sprinters Club', 3);
INSERT INTO club (name, address_id) VALUES ('Houston Marathon Club', 4);
INSERT INTO club (name, address_id) VALUES ('Phoenix Jumpers Club', 5);
INSERT INTO club (name, address_id) VALUES ('Philadelphia Throwers Club', 6);
INSERT INTO club (name, address_id) VALUES ('San Antonio Vaulters Club', 7);
INSERT INTO club (name, address_id) VALUES ('San Diego Decathlon Club', 8);
INSERT INTO club (name, address_id) VALUES ('Dallas Hurdles Club', 9);
INSERT INTO club (name, address_id) VALUES ('San Jose Relay Club', 10);

INSERT INTO gender_type (name) VALUES ('MALE');
INSERT INTO gender_type (name) VALUES ('FEMALE');

INSERT INTO age_group (name, min_age, max_age) VALUES ('CHILD', 6, 9);
INSERT INTO age_group (name, min_age, max_age) VALUES ('YOUTH', 10, 14);
INSERT INTO age_group (name, min_age, max_age) VALUES ('JUNIOR', 14, 22);
INSERT INTO age_group (name, min_age, max_age) VALUES ('ADULT', 23, 40);
INSERT INTO age_group (name, min_age, max_age) VALUES ('SENIOR', 41, 150);

INSERT INTO result_type (name) VALUES ('TIME');
INSERT INTO result_type (name) VALUES ('DISTANCE');
INSERT INTO result_type (name) VALUES ('HEIGHT');