-- data.sql will be executed after this file

INSERT INTO category (name) VALUES ('Vegetarian'); -- id: 1
INSERT INTO category (name) VALUES ('Vegan'); -- id: 2
INSERT INTO category (name) VALUES ('Fish'); -- id: 3
INSERT INTO category (name) VALUES ('Beef'); -- id: 4
INSERT INTO category (name) VALUES ('Chicken'); -- id: 5
INSERT INTO category (name) VALUES ('Pork'); -- id: 6
INSERT INTO category (name) VALUES ('Italian'); -- id: 7
INSERT INTO category (name) VALUES ('Mexican'); -- id: 8
INSERT INTO category (name) VALUES ('Chinese'); -- id: 9
INSERT INTO category (name) VALUES ('Thai'); -- id: 10
INSERT INTO category (name) VALUES ('German'); -- id: 11
INSERT INTO category (name) VALUES ('Swiss'); -- id: 12


INSERT INTO unit_of_measurement (name) VALUES ('Tablespoon'); -- 1
INSERT INTO unit_of_measurement (name) VALUES ('Teaspoon'); -- 2
INSERT INTO unit_of_measurement (name) VALUES ('Cup'); -- 3
INSERT INTO unit_of_measurement (name) VALUES ('Liter'); -- 4
INSERT INTO unit_of_measurement (name) VALUES ('Milliliter'); -- 5
INSERT INTO unit_of_measurement (name) VALUES ('Kilogram'); -- 6
INSERT INTO unit_of_measurement (name) VALUES ('Gram'); -- 7
INSERT INTO unit_of_measurement (name) VALUES ('Slice'); -- 8
INSERT INTO unit_of_measurement (name) VALUES ('Some'); -- 9
INSERT INTO unit_of_measurement (name) VALUES ('Pinch'); -- 10
INSERT INTO unit_of_measurement (name) VALUES ('Can'); -- 11
INSERT INTO unit_of_measurement (name) VALUES (''); -- 12
INSERT INTO unit_of_measurement (name) VALUES ('Dash'); -- 13



INSERT INTO nutritional_info (text)
VALUES ('Very light. Eat all you want.'),
       ('Eat to much and you''ll be fat');

INSERT INTO instruction (text)
VALUES ('1. Grate potatoes on the raffle in a bowl. 2. Add salt to grated potatoes. 3. Melt margarine in a frying pan. Add potatoes, fry with occasional turning for about 5 minutes.Use a scoop to form the rösti into a cake, don''t move any further. Roast on medium heat for about 15 minutes. Put a flat plate on the pan and top with the rösti. Put a little bit of butter in the pan, let the rösti slide back into the pan, open fry for about 15 minutes.'),
       ('1. Bring water to a boil. 2. Add Spaghetti. 3. Cook for 11 minutes. 4. Add tomato sauce. 5. Heat up until everything is warm.');

INSERT INTO recipe (cooking_duration, difficulty, image, preparation_duration, servings, source, title, url, instruction_id, nutritional_info_id)
VALUES (30, 'AMATEUR', null, 5, 4, 'https://www.bettybossi.ch/', 'Rösti', 'https://www.bettybossi.ch/de/Rezept/ShowRezept/BB_CHCH090801_0107A-40-de', 1, 1),
       (11, 'BEGINNER', null, 5, 4, 'Common Sense', 'Spaghetti with Tomato Sauce', null, 2, 2);

INSERT INTO recipe_category (recipe_id, category_id)
VALUES (1 , 2), (1,12),
       (2, 1), (1,7);

INSERT INTO product (name)
VALUES ('Potatoes'), ('Salt'), ('Margarine'),
       ('Spaghetti'), ('Tomato Sauce');

INSERT INTO rating (ip, rating, recipe_id)
VALUES ('8.8.8.8', 8, 1), ('10.5.2.1', 5, 1), ('192.168.1.10', 10, 1),
       ('8.8.8.8', 4, 2), ('10.5.2.1', 3, 2), ('192.168.1.10', 7, 2);

INSERT INTO ingredient (amount, product_id, recipe_id, unit_of_measurement_id)
VALUES (1, 1, 1, 6), (1, 2, 1, 2), (null, 3, 1, 9),
       (300, 4, 2, 7), (2, 2, 2, 2), (2, 5, 2, 11);




