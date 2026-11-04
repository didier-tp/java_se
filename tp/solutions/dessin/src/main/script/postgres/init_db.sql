CREATE DATABASE IF NOT EXISTS drawing_db;
USE drawing_db;
DROP TABLE IF EXISTS dessin;
CREATE TABLE dessin  (id SERIAL NOT NULL,
                        titre VARCHAR(64),
                        path VARCHAR(255),
                        description VARCHAR(64),
                        PRIMARY KEY(id));

INSERT INTO dessin(titre,path,description) VALUES ('dessin1','dessin.svg', 'généré par code java');

show tables;
describe dessin;
SELECT * FROM dessin;