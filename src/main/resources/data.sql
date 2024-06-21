INSERT INTO role (role_name) VALUES ('USER');
INSERT INTO role (role_name) VALUES ('ADMIN');
INSERT INTO role (role_name) VALUES ('MANAGER');

INSERT INTO result_type (name) VALUES ('TIME');
INSERT INTO result_type (name) VALUES ('DISTANCE');
INSERT INTO result_type (name) VALUES ('HEIGHT');

INSERT INTO discipline (name, description, result_type_id) VALUES ('100m Sprint', 'A short-distance sprint race in track and field.', (SELECT id FROM result_type WHERE name = 'TIME'));
INSERT INTO discipline (name, description, result_type_id) VALUES ('Marathon', 'A long-distance running race of 42.195 kilometers.', (SELECT id FROM result_type WHERE name = 'TIME'));
INSERT INTO discipline (name, description, result_type_id) VALUES ('High Jump', 'An event where athletes jump over a horizontal bar at measured heights.', (SELECT id FROM result_type WHERE name = 'HEIGHT'));
INSERT INTO discipline (name, description, result_type_id) VALUES ('Pole Vault', 'An event where athletes use a pole to jump over a high bar.', (SELECT id FROM result_type WHERE name = 'HEIGHT'));
INSERT INTO discipline (name, description, result_type_id) VALUES ('Long Jump', 'An event where athletes jump as far as possible from a take-off point.', (SELECT id FROM result_type WHERE name = 'DISTANCE'));
INSERT INTO discipline (name, description, result_type_id) VALUES ('Triple Jump', 'An event where athletes jump as far as possible in three consecutive jumps.', (SELECT id FROM result_type WHERE name = 'DISTANCE'));
INSERT INTO discipline (name, description, result_type_id) VALUES ('Shot Put', 'An event where athletes throw a heavy metal ball as far as possible.', (SELECT id FROM result_type WHERE name = 'DISTANCE'));
INSERT INTO discipline (name, description, result_type_id) VALUES ('Discus Throw', 'An event where athletes throw a heavy disc as far as possible.', (SELECT id FROM result_type WHERE name = 'DISTANCE'));
INSERT INTO discipline (name, description, result_type_id) VALUES ('Javelin Throw', 'An event where athletes throw a spear as far as possible.', (SELECT id FROM result_type WHERE name = 'DISTANCE'));
INSERT INTO discipline (name, description, result_type_id) VALUES ('Hammer Throw', 'An event where athletes throw a heavy ball as far as possible.', (SELECT id FROM result_type WHERE name = 'DISTANCE'));

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

-- Inserting participant 1 with relation to discipline 1 and 2
INSERT INTO participant (first_name, last_name, birth_date, gender_type_id, club_id) VALUES ('John', 'Doe', '1980-01-01', (SELECT id FROM gender_type WHERE name = 'MALE'), (SELECT id FROM club WHERE name = 'New York Athletics Club'));
INSERT INTO participant_discipline (participant_id, discipline_id) VALUES ((SELECT id FROM participant WHERE first_name = 'John' AND last_name = 'Doe'), (SELECT id FROM discipline WHERE name = '100m Sprint'));
INSERT INTO participant_discipline (participant_id, discipline_id) VALUES ((SELECT id FROM participant WHERE first_name = 'John' AND last_name = 'Doe'), (SELECT id FROM discipline WHERE name = 'Marathon'));

-- Inserting participant 2 with relation to discipline 3
INSERT INTO participant (first_name, last_name, birth_date, gender_type_id, club_id) VALUES ('Jane', 'Doe', '1985-02-02', (SELECT id FROM gender_type WHERE name = 'FEMALE'), (SELECT id FROM club WHERE name = 'Los Angeles Runners Club'));
INSERT INTO participant_discipline (participant_id, discipline_id) VALUES ((SELECT id FROM participant WHERE first_name = 'Jane' AND last_name = 'Doe'), (SELECT id FROM discipline WHERE name = 'High Jump'));

-- Inserting participant 3 with relation to discipline 4 and 5
INSERT INTO participant (first_name, last_name, birth_date, gender_type_id, club_id) VALUES ('Bob', 'Smith', '1990-03-03', (SELECT id FROM gender_type WHERE name = 'MALE'), (SELECT id FROM club WHERE name = 'Chicago Sprinters Club'));
INSERT INTO participant_discipline (participant_id, discipline_id) VALUES ((SELECT id FROM participant WHERE first_name = 'Bob' AND last_name = 'Smith'), (SELECT id FROM discipline WHERE name = 'Pole Vault'));
INSERT INTO participant_discipline (participant_id, discipline_id) VALUES ((SELECT id FROM participant WHERE first_name = 'Bob' AND last_name = 'Smith'), (SELECT id FROM discipline WHERE name = 'Long Jump'));

-- Inserting participant 4 with relation to discipline 6
INSERT INTO participant (first_name, last_name, birth_date, gender_type_id, club_id) VALUES ('Alice', 'Johnson', '1995-04-04', (SELECT id FROM gender_type WHERE name = 'FEMALE'), (SELECT id FROM club WHERE name = 'Houston Marathon Club'));
INSERT INTO participant_discipline (participant_id, discipline_id) VALUES ((SELECT id FROM participant WHERE first_name = 'Alice' AND last_name = 'Johnson'), (SELECT id FROM discipline WHERE name = 'Triple Jump'));

-- Inserting participant 5 with relation to discipline 7 and 8
INSERT INTO participant (first_name, last_name, birth_date, gender_type_id, club_id) VALUES ('Charlie', 'Brown', '2000-05-05', (SELECT id FROM gender_type WHERE name = 'MALE'), (SELECT id FROM club WHERE name = 'Phoenix Jumpers Club'));
INSERT INTO participant_discipline (participant_id, discipline_id) VALUES ((SELECT id FROM participant WHERE first_name = 'Charlie' AND last_name = 'Brown'), (SELECT id FROM discipline WHERE name = 'Shot Put'));
INSERT INTO participant_discipline (participant_id, discipline_id) VALUES ((SELECT id FROM participant WHERE first_name = 'Charlie' AND last_name = 'Brown'), (SELECT id FROM discipline WHERE name = 'Discus Throw'));

-- Inserting result 1 for participant 1
INSERT INTO result (result_date, result_value, participant_id, discipline_id) VALUES ('2022-01-01', 10.5, (SELECT id FROM participant WHERE first_name = 'John' AND last_name = 'Doe'), (SELECT id FROM discipline WHERE name = '100m Sprint'));

-- Inserting result 2 for participant 1
INSERT INTO result (result_date, result_value, participant_id, discipline_id) VALUES ('2022-02-01', 2.5, (SELECT id FROM participant WHERE first_name = 'John' AND last_name = 'Doe'), (SELECT id FROM discipline WHERE name = 'Marathon'));

-- Inserting result 1 for participant 2
INSERT INTO result (result_date, result_value, participant_id, discipline_id) VALUES ('2022-03-01', 1.75, (SELECT id FROM participant WHERE first_name = 'Jane' AND last_name = 'Doe'), (SELECT id FROM discipline WHERE name = 'High Jump'));

-- Inserting result 2 for participant 2
INSERT INTO result (result_date, result_value, participant_id, discipline_id) VALUES ('2022-04-01', 5.5, (SELECT id FROM participant WHERE first_name = 'Jane' AND last_name = 'Doe'), (SELECT id FROM discipline WHERE name = 'Triple Jump'));