-- Before this is executed,
-- data-development.sql or data-production.sql will be loaded,
-- dependent on what is set for "spring.datasource.platform:" in application.yaml
-- Create the file schema.sql/schema-development.sql/schema-production.sql in case
-- you want to define the database "by hand".


-- Spring doesn't like it if this file is empty, so we need to put something in here
INSERT INTO category (name) VALUES ('American');