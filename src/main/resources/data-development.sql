-- data.sql will be executed after this file

INSERT INTO category (name) VALUES ('Swiss');


INSERT INTO nutritional_info
(text)
VALUES ('You will become obese');

INSERT INTO instruction
(text)
VALUES ('Just do it');

INSERT INTO recipe
(cooking_duration, difficulty, image, preparation_duration, servings, source, title, url, instruction_id, nutritional_info_id)
VALUES
  (30, 'HARD', null, 30, 4, 'www.food.com', 'Rösti', 'www.food.com/roesti', 1, 1);