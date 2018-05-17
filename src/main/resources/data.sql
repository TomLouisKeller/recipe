-- Before this is executed,
-- data-development.sql or data-production.sql will be loaded,
-- dependent on what is set for "spring.datasource.platform:" in application.yaml
-- Create the file schema.sql/schema-development.sql/schema-production.sql in case
-- you want to define the database "by hand".

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

INSERT INTO unit_of_measurement (name) VALUES ('Tablespoon');
INSERT INTO unit_of_measurement (name) VALUES ('Teaspoon');
INSERT INTO unit_of_measurement (name) VALUES ('Cup');
INSERT INTO unit_of_measurement (name) VALUES ('Liter');
INSERT INTO unit_of_measurement (name) VALUES ('Milliliter');
INSERT INTO unit_of_measurement (name) VALUES ('Kilogram');
INSERT INTO unit_of_measurement (name) VALUES ('Gram');
INSERT INTO unit_of_measurement (name) VALUES ('Slice');
