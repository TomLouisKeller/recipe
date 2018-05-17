-- data.sql will be executed after this file

INSERT INTO category (name) VALUES ('Vegetarian');
INSERT INTO category (name) VALUES ('Vegan');
INSERT INTO category (name) VALUES ('Fish');
INSERT INTO category (name) VALUES ('Beef');
INSERT INTO category (name) VALUES ('Chicken');
INSERT INTO category (name) VALUES ('Pork');
INSERT INTO category (name) VALUES ('Italian');
INSERT INTO category (name) VALUES ('Mexican');
INSERT INTO category (name) VALUES ('Chinese');
INSERT INTO category (name) VALUES ('Thai');
INSERT INTO category (name) VALUES ('German');
INSERT INTO category (name) VALUES ('Swiss');


INSERT INTO unit_of_measurement (name) VALUES ('Tablespoon');
INSERT INTO unit_of_measurement (name) VALUES ('Teaspoon');
INSERT INTO unit_of_measurement (name) VALUES ('Cup');
INSERT INTO unit_of_measurement (name) VALUES ('Liter');
INSERT INTO unit_of_measurement (name) VALUES ('Milliliter');
INSERT INTO unit_of_measurement (name) VALUES ('Kilogram');
INSERT INTO unit_of_measurement (name) VALUES ('Gram');
INSERT INTO unit_of_measurement (name) VALUES ('Slice');
INSERT INTO unit_of_measurement (name) VALUES ('Some');

INSERT INTO nutritional_info (text)
VALUES ('Very light. Eat all you want.');

INSERT INTO instruction (text)
VALUES ('Just do it');

INSERT INTO recipe (cooking_duration, difficulty, image, preparation_duration, servings, source, title, url, instruction_id, nutritional_info_id)
VALUES (30, 'BEGINNER', null, 5, 4, 'https://www.bettybossi.ch/', 'Rösti', 'https://www.bettybossi.ch/de/Rezept/ShowRezept/BB_CHCH090801_0107A-40-de', 1, 1);

INSERT INTO recipe_category (recipe_id, category_id)
VALUES (1 , 2), (1,12);

INSERT INTO product (name)
VALUES ('Potatoes'), ('Salt'), ('Margarine');

INSERT INTO rating (ip, rating, recipe_id)
VALUES ('8.8.8.8', 8, 1), ('10.5.2.1', 5, 1), ('192.168.1.10', 10, 1);

INSERT INTO ingredient (amount, product_id, recipe_id, unit_of_measurement_id)
VALUES (1, 1, 1, 6), (1, 2, 1, 2), (-1, 3, 1, 9);
